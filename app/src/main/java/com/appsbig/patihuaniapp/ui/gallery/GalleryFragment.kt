package com.appsbig.patihuaniapp.ui.gallery

import android.app.DatePickerDialog
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appsbig.patihuaniapp.R
import com.appsbig.patihuaniapp.databinding.FragmentGalleryBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private var selectedImageUri: Uri? = null

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        database = FirebaseDatabase.getInstance().reference

        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK && result.data != null) {
                selectedImageUri = result.data?.data
                binding.imagenPerfil.setImageURI(selectedImageUri)
            }
        }

        binding.imagenPerfil.setImageResource(R.drawable.perfil)

        binding.botonAgregaFoto.setOnClickListener {
            pickImageFromGallery()
        }

        binding.editarFechaNacimiento.setOnClickListener {
            showDatePickerDialog()
        }

        binding.editarPeso.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        binding.editarAltura.inputType = android.text.InputType.TYPE_CLASS_NUMBER

        setupSpinner(binding.spEstilosVida, R.array.estilosvida)
        setupSpinner(binding.spActFisica, R.array.actividadfisica)

        binding.infoExtra.inputType = android.text.InputType.TYPE_CLASS_TEXT

        binding.botonUbicacion.setOnClickListener {
            mostrarOpcionesDeUbicacion()
        }

        // Botón para actualizar perfil
        binding.btnActualizarP.setOnClickListener {
            actualizarPerfilUsuario()
        }

        return root
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    private fun setupSpinner(spinner: Spinner, arrayId: Int) {
        ArrayAdapter.createFromResource(
            requireContext(),
            arrayId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            binding.editarFechaNacimiento.setText("${selectedDay}/${selectedMonth + 1}/${selectedYear}")
        }, year, month, day).show()
    }

    private fun mostrarOpcionesDeUbicacion() {
        val ubicaciones = arrayOf("Ubicación 1", "Ubicación 2", "Ubicación 3")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Seleccione una ubicación")
        builder.setItems(ubicaciones) { _, which ->
            binding.ubicacionSeleccionada.text = ubicaciones[which]
        }
        builder.show()
    }

    private fun actualizarPerfilUsuario() {
        val nombre = "Nombre de usuario"
        val apellidos = "Apellidos del usuario"
        val correo = "correo@ejemplo.com"
        val contrasena = "contraseña"
        val ubicacion = binding.ubicacionSeleccionada.text.toString()
        val infoExtraTexto = limpiarSepararTexto(binding.infoExtra.text.toString()).joinToString(" ")

        val usuario = Usuario(
            nombre = nombre,
            apellidos = apellidos,
            correo = correo,
            contrasena = contrasena,
            fotoPerfil = selectedImageUri,
            fechaNacimiento = binding.editarFechaNacimiento.text.toString(),
            peso = binding.editarPeso.text.toString().toDoubleOrNull(),
            altura = binding.editarAltura.text.toString().toDoubleOrNull(),
            opcionLista = binding.spEstilosVida.selectedItem.toString(),
            otraOpcionLista = binding.spActFisica.selectedItem.toString(),
            ubicacion = ubicacion,
            infoExtra = infoExtraTexto
        )

        usuario.actualizarPerfil(
            fotoPerfil = selectedImageUri?.toString(),
            fechaNacimiento = binding.editarFechaNacimiento.text.toString(),
            peso = binding.editarPeso.text.toString().toDoubleOrNull(),
            altura = binding.editarAltura.text.toString().toDoubleOrNull(),
            opcionLista = binding.spEstilosVida.selectedItem.toString(),
            otraOpcionLista = binding.spActFisica.selectedItem.toString(),
            ubicacion = ubicacion,
            infoExtra = infoExtraTexto
        )

        val edad = usuario.calcularEdad()
        println("La edad del usuario es: $edad")

        database.child("usuarios").child(correo.replace(".", "_")).setValue(usuario)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error al actualizar perfil: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limpiarSepararTexto(texto: String): Array<String> {
        // Lista de stopwords en español
        val stopwords = setOf(
            "a", "actualmente", "adelante", "además", "afirmó", "agregó", "ahora", "ahí", "al",
            "algo", "alguna", "algunas", "alguno", "algunos", "algún", "alrededor", "ambos",
            "ampleamos", "ante", "anterior", "antes", "apenas", "aproximadamente", "aquel",
            "aquellas", "aquellos", "aqui", "aquí", "arriba", "aseguró", "así", "atras",
            "aunque", "ayer", "añadió", "aún", "bajo", "bastante", "bien", "buen", "buena",
            "buenas", "bueno", "buenos", "cada", "casi", "cerca", "cierta", "ciertas", "cierto",
            "ciertos", "cinco", "comentó", "como", "con", "conocer", "conseguimos", "conseguir",
            "considera", "consideró", "consigo", "consigue", "consiguen", "consigues", "contra",
            "cosas", "creo", "cual", "cuales", "cualquier", "cuando", "cuanto", "cuatro",
            "cuenta", "cómo", "da", "dado", "dan", "dar", "de", "debe", "deben", "debido", "decir",
            "dejó", "del", "demás", "dentro", "desde", "después", "dice", "dicen", "dicho",
            "dieron", "diferente", "diferentes", "dijeron", "dijo", "dio", "donde", "dos",
            "durante", "e", "ejemplo", "el", "ella", "ellas", "ello", "ellos", "embargo",
            "empleais", "emplean", "emplear", "empleas", "empleo", "en", "encima", "encuentra",
            "entonces", "entre", "era", "erais", "eramos", "eran", "eras", "eres", "es", "esa",
            "esas", "ese", "eso", "esos", "esta", "estaba", "estabais", "estaban", "estabas",
            "estad", "estada", "estadas", "estado", "estados", "estais", "estamos", "estan",
            "estando", "estar", "estaremos", "estará", "estarán", "estarás", "estaré", "estaréis",
            "estaría", "estaríais", "estaríamos", "estarían", "estarías", "estas", "este",
            "estemos", "esto", "estos", "estoy", "estuve", "estuviera", "estuvierais",
            "estuvieran", "estuvieras", "estuvieron", "estuviese", "estuvieseis", "estuviesen",
            "estuvieses", "estuvimos", "estuviste", "estuvisteis", "estuviéramos", "estuviésemos",
            "estuvo", "está", "estábamos", "estáis", "están", "estás", "esté", "estéis", "estén",
            "estés", "ex", "existe", "existen", "explicó", "expresó", "fin", "fue", "fuera",
            "fuerais", "fueran", "fueras", "fueron", "fuese", "fueseis", "fuesen", "fueses",
            "fui", "fuimos", "fuiste", "fuisteis", "fuéramos", "fuésemos", "gran", "grandes",
            "gueno", "ha", "haber", "habida", "habidas", "habido", "habidos", "habiendo",
            "habremos", "habrá", "habrán", "habrás", "habré", "habréis", "habría", "habríais",
            "habríamos", "habrían", "habrías", "habéis", "había", "habíais", "habíamos", "habían",
            "habías", "hace", "haceis", "hacemos", "hacen", "hacer", "hacerlo", "haces", "hacia",
            "haciendo", "hago", "han", "has", "hasta", "hay", "haya", "hayamos", "hayan", "hayas",
            "hayáis", "he", "hecho", "hemos", "hicieron", "hizo", "hoy", "hube", "hubiera",
            "hubierais", "hubieran", "hubieras", "hubieron", "hubiese", "hubieseis", "hubiesen",
            "hubieses", "hubimos", "hubiste", "hubisteis", "hubiéramos", "hubiésemos", "hubo",
            "igual", "incluso", "indicó", "informó", "intenta", "intentais", "intentamos",
            "intentan", "intentar", "intentas", "intento", "ir", "junto", "la", "lado", "largo",
            "las", "le", "les", "llegó", "lleva", "llevar", "lo", "los", "luego", "lugar",
            "manera", "manifestó", "mayor", "me", "mediante", "mejor", "mencionó", "menos", "mi",
            "mientras", "mio", "mis", "misma", "mismas", "mismo", "mismos", "modo", "momento",
            "mucha", "muchas", "mucho", "muchos", "muy", "más", "mí", "mía", "mías", "mío",
            "míos", "nada", "nadie", "ni", "ninguna", "ningunas", "ninguno", "ningunos", "ningún",
            "no", "nos", "nosotras", "nosotros", "nuestra", "nuestras", "nuestro", "nuestros",
            "nueva", "nuevas", "nuevo", "nuevos", "nunca", "o", "ocho", "os", "otra", "otras",
            "otro", "otros", "para", "parece", "parte", "partir", "pasada", "pasado", "pero",
            "pesar", "poca", "pocas", "poco", "pocos", "podeis", "podemos", "poder", "podria",
            "podriais", "podriamos", "podrian", "podrias", "podrá", "podrán", "podría", "podrían",
            "poner", "por", "por qué", "porque", "posible", "primer", "primera", "primero",
            "primeros", "principalmente", "propia", "propias", "propio", "propios", "próximo",
            "próximos", "pudo", "pueda", "puede", "pueden", "puedo", "pues", "que", "quedó",
            "queremos", "quien", "quienes", "quiere", "quién", "qué", "realizado", "realizar",
            "realizó", "respecto", "sabe", "sabeis", "sabemos", "saben", "saber", "sabes", "se",
            "sea", "seamos", "sean", "seas", "segunda", "segundo", "según", "seis", "ser",
            "seremos", "será", "serán", "serás", "seré", "seréis", "sería", "seríais",
            "seríamos", "serían", "serías", "seáis", "señaló", "si", "sido", "siempre", "siendo",
            "siete", "sigue", "siguiente", "sin", "sino", "sobre", "sois", "sola", "solamente",
            "solas", "solo", "solos", "somos", "son", "soy", "su", "sus", "suya", "suyas",
            "suyo", "suyos", "sí", "sólo", "tal", "también", "tampoco", "tan", "tanto", "te",
            "tendremos", "tendrá", "tendrán", "tendrás", "tendré", "tendréis", "tendría",
            "tendríais", "tendríamos", "tendrían", "tendrías", "tened", "teneis", "tenemos",
            "tener", "tenga", "tengamos", "tengan", "tengas", "tengo", "tengáis", "tenida",
            "tenidas", "tenido", "tenidos", "teniendo", "tenéis", "tenía", "teníais", "teníamos",
            "tenían", "tenías", "tercera", "ti", "tiempo", "tiene", "tienen", "tienes", "toda",
            "todas", "todavía", "todo", "todos", "total", "trabaja", "trabajais", "trabajamos",
            "trabajan", "trabajar", "trabajas", "trabajo", "tras", "trata", "través", "tres",
            "tu", "tus", "tuve", "tuviera", "tuvierais", "tuvieran", "tuvieras", "tuvieron",
            "tuviese", "tuvieseis", "tuviesen", "tuvieses", "tuvimos", "tuviste", "tuvisteis",
            "tuviéramos", "tuviésemos", "tuvo", "tuya", "tuyas", "tuyo", "tuyos", "tú", "ultimo",
            "un", "una", "unas", "uno", "unos", "usa", "usais", "usamos", "usan", "usar", "usas",
            "uso", "usted", "va", "vais", "valor", "vamos", "van", "varias", "varios", "vaya",
            "veces", "ver", "verdad", "verdadera", "verdadero", "vez", "vosotras", "vosotros",
            "voy", "vuestra", "vuestras", "vuestro", "vuestros", "y", "ya", "yo", "él", "éramos",
            "ésta", "éstas", "éste", "éstos", "última", "últimas", "último", "últimos"
        )

        // Dividir el texto en oraciones usando los signos de puntuación como delimitadores
        val oraciones = texto.split(Regex("[.!?]")).filter { it.isNotBlank() }

        // Para cada oración, separar en palabras, limpiar cada palabra y filtrar las vacías y stopwords
        val palabrasLimpias = mutableListOf<String>()
        for (oracion in oraciones) {
            val palabras = oracion.split(Regex("\\s+")) // Dividir por uno o más espacios
            for (palabra in palabras) {
                val palabraLimpia = palabra.replace(Regex("[^\\w]"), ",").lowercase()
                if (palabraLimpia.isNotBlank() && palabraLimpia !in stopwords) {
                    palabrasLimpias.add(palabraLimpia)
                }
            }
        }
        return palabrasLimpias.toTypedArray()
    }

}