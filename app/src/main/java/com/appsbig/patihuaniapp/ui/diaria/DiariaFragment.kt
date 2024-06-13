package com.appsbig.patihuaniapp.ui.diaria

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.appsbig.patihuaniapp.Navegacion
import com.appsbig.patihuaniapp.R
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class DiariaFragment : Fragment() {

    companion object {
        fun newInstance() = DiariaFragment()
    }

    private val viewModel: DiariaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val raiz = inflater.inflate(R.layout.fragment_diaria, container, false)
        val imagen = raiz.findViewById<ImageView>(R.id.dieta_diaria_whats) // define la variable imagen como un ImageView
        val stringMsj="Esta app es un milagro me recomendo  una dieta hoy aqui para que te recomiende tambien: https://play.google.com/store/apps/details?id=com.appsbig.patihuaapp&hl=es_MX&gl=US" // define la variable stringMsj como un String
        imagen.setOnClickListener {// cuando se hace click en la imagen
            Whatsapp.compartirMensajeTexto(requireContext(), stringMsj)
            // llama a la funcion compartirMensajeTexto de la clase Whatsapp
        }

        val backButton = raiz.findViewById<Button>(R.id.back_DietaDiaria)
        backButton.setOnClickListener {
            val intent = Intent(requireContext(), Navegacion::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return raiz
    }
}