package com.appsbig.patihuaniapp.ui.actividadescomplementarias

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
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

        val textView: TextView = binding.textView99
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}