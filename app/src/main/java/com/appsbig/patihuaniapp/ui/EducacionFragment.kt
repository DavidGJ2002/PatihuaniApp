package com.appsbig.patihuaniapp.ui

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.appsbig.patihuaniapp.Bienvenido
import com.appsbig.patihuaniapp.Caminar
import com.appsbig.patihuaniapp.R

class EducacionFragment : Fragment() {

    companion object {
        fun newInstance() = EducacionFragment()
    }

    private val viewModel: EducacionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val raiz = inflater.inflate(R.layout.fragment_educacion, container,false)
        val button8 = raiz.findViewById<Button>(R.id.button8)
        val button9 = raiz.findViewById<Button>(R.id.button9)
        val button10 = raiz.findViewById<Button>(R.id.button10)
        val button11 = raiz.findViewById<Button>(R.id.button11)
        val backButton = raiz.findViewById<Button>(R.id.button_back)

        // Configurar el botón de regreso
        backButton.setOnClickListener {
            val intent = Intent(requireContext(), Bienvenido::class.java)
            startActivity(intent)

        }

        // Agregar listeners a los botones
        button8.setOnClickListener {
            val intent = Intent(requireContext(), Caminar::class.java)
            intent.putExtra("boton_seleccionado", "etiquetado")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }

        button9.setOnClickListener {
            val intent = Intent(requireContext(), Caminar::class.java)
            intent.putExtra("boton_seleccionado", "hipertencion")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }

        button10.setOnClickListener {
            val intent = Intent(requireContext(), Caminar::class.java)
            intent.putExtra("boton_seleccionado", "lacteos")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }

        button11.setOnClickListener {
            val intent = Intent(requireContext(), Caminar::class.java)
            intent.putExtra("boton_seleccionado", "pan")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }

        return  raiz

    }
}