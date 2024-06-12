package com.appsbig.patihuaniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class Caminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el identificador del layout a cargar según el botón seleccionado
        val botonSeleccionado = intent.getStringExtra("boton_seleccionado")
        val layoutId = when (botonSeleccionado) {
            "caminar" -> R.layout.activity_caminar
            "nadar" -> R.layout.activity_nadar
            "saltar" -> R.layout.activity_saltar
            "correr" -> R.layout.activity_correr
            "Dieta1"-> R.layout.activity_tu_dieta1
                "Dieta2"-> R.layout.activity_tu_dieta2
                "Alternativa1"-> R.layout.activity_tu_alternativa1
            "Alternativa2"-> R.layout.activity_tu_alternativa2
            "etiquetado" -> R.layout.activity_educacion_etiquetado
            "hipertencion" -> R.layout.activity_educacion_hipertencion
            "lacteos" -> R.layout.activity_educacion_lacteos
            "pan" -> R.layout.activity_educacion_pan
            else -> {
                // Si el botón seleccionado no coincide con ninguna actividad, muestra un mensaje de error y cierra la actividad
                Toast.makeText(this, "Actividad no válida", Toast.LENGTH_SHORT).show()
                finish()
                return
            }
        }

        // Establecer el layout correspondiente
        setContentView(layoutId)

        // Configurar el botón de compartir
        /*val imagenCompartir = findViewById<ImageView>(R.id.compartirCaminar)
        imagenCompartir.setOnClickListener {
            val stringMsj = "me recomendó una noticia que me cambiará la vida aquí para que te recomiende también: https://play.google.com/store/apps/details?id=com.appsbig.patihuaapp&hl=es_MX&gl=US"
            Whatsapp.compartirMensajeTexto(this, stringMsj)
        }*/

        // Obtener el identificador de la actividad de regreso
        val returnActivity = intent.getStringExtra("return_activity")
        val backCaminar = findViewById<Button>(R.id.back_Regreso)
        backCaminar.setOnClickListener {
            val intent = when (returnActivity) {
                "ActividadesComplementarias" -> Intent(this, ActividadesComplementarias::class.java)
                "EducacionNutricional" -> Intent(this, EducacionNutricional::class.java)
                "alternativasDietas" -> Intent(this, alternativasDietas::class.java)
                else -> Intent(this, Bienvenido::class.java) // Default case
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}
