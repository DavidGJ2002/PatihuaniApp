package com.appsbig.patihuaniapp.ui.actividadescomplementarias

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.appsbig.patihuaniapp.Bienvenido
import com.appsbig.patihuaniapp.Caminar
import com.appsbig.patihuaniapp.R
import com.appsbig.patihuaniapp.databinding.FragmentActividadesComplementariasBinding


class actividadesComplementariasFragment : Fragment() {
    private var _binding: FragmentActividadesComplementariasBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ActividadesComplementariasViewModel::class.java)

        _binding = FragmentActividadesComplementariasBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Configurar el botón de regreso

        binding.buttonBack.setOnClickListener {
            val intent = Intent(requireContext(), Bienvenido::class.java)
            startActivity(intent)

        }


        // Agregar listeners a los botones
        binding.button8.setOnClickListener {
            abrirActividad("caminar")
        }

        binding.button9.setOnClickListener {
            abrirActividad("correr")
        }

        binding.button10.setOnClickListener {
            abrirActividad("nadar")
        }

        binding.button11.setOnClickListener {
            abrirActividad("saltar")
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun abrirActividad(botonSeleccionado: String) {
        val intent = Intent(requireContext(), Caminar::class.java)
        intent.putExtra("boton_seleccionado", botonSeleccionado)
        intent.putExtra("return_activity", "ActividadesComplementarias")
        startActivity(intent)
    }
}