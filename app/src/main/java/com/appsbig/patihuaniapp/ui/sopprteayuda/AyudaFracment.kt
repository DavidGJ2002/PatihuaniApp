package com.appsbig.patihuaniapp.ui.sopprteayuda

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.appsbig.patihuaniapp.IniciarSesion
import com.appsbig.patihuaniapp.R
import com.appsbig.patihuaniapp.ui.PersonalizarRecomendaciones
import com.appsbig.patihuaniapp.ui.Soporte

class AyudaFracment : Fragment() {

    companion object {
        fun newInstance() = AyudaFracment()
    }

    private val viewModel: AyudaFracmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val raiz = inflater.inflate(R.layout.fragment_ayuda_fracment, container, false)
        val btnAyuda = raiz.findViewById<Button>(R.id.cerrarS)
        btnAyuda.setOnClickListener {
            val intent = Intent(context, IniciarSesion::class.java)
            startActivity(intent)
            activity?.finish()
        }
        val imageFaq = raiz.findViewById<ImageView>(R.id.faqV)
        imageFaq.setOnClickListener {
            val intent = Intent(context, Soporte::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return  raiz
    }
}