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

class AlternativaFragment : Fragment() {

    companion object {
        fun newInstance() = AlternativaFragment()
    }

    private val viewModel: AlternativaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


    val raizView = inflater.inflate(R.layout.fragment_alternativa, container, false)

        val backButton = raizView.findViewById<Button>(R.id.button_back)
        backButton.setOnClickListener {
            val intent = Intent(requireContext(), Bienvenido::class.java)
            startActivity(intent)

        }

        // Buscar los botones en el layout
        val button8 = raizView.findViewById<Button>(R.id.button8)
        val button9 = raizView.findViewById<Button>(R.id.button9)
        val button10 = raizView.findViewById<Button>(R.id.button10)
        val button11 = raizView.findViewById<Button>(R.id.button11)

        // Agregar listeners a los botones
        button8.setOnClickListener {
            abrirActividad("Dieta1")
        }

        button9.setOnClickListener {
            abrirActividad("Dieta2")
        }

        button10.setOnClickListener {
            abrirActividad("Alternativa1")
        }

        button11.setOnClickListener {
            abrirActividad("Alternativa2")
        }

    return raizView
}
private fun abrirActividad(botonSeleccionado: String) {
    val intent = Intent(requireContext(), Caminar::class.java)
    intent.putExtra("boton_seleccionado", botonSeleccionado)
    intent.putExtra("return_activity", "alternativasDietas")
    startActivity(intent)
}
}