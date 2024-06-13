package com.appsbig.patihuaniapp.ui.metas

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.appsbig.patihuaniapp.R
import com.appsbig.patihuaniapp.alvaro.MetasActivities
import com.appsbig.patihuaniapp.alvaro.ProgresoActivity

class MetasFragment : Fragment() {

    companion object {
        fun newInstance() = MetasFragment()
        private const val SOLICITUD_AGREGAR_META = 1
    }

    private val viewModel: MetasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root=  inflater.inflate(R.layout.fragment_metas, container, false)
        val listView = root.findViewById<ListView>(R.id.lv_metas)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, viewModel.metas)
        listView.adapter = adapter
        val button = root.findViewById<Button>(R.id.bt_abrir_progreso)
        button.setOnClickListener {
            val intent = Intent(requireContext(), ProgresoActivity::class.java)
            startActivity(intent)
        }
        val button2 = root.findViewById<Button>(R.id.bt_agreagar_meta)
        button2.setOnClickListener {
            val intent = Intent(requireContext(), MetasActivities::class.java)
            startActivity(intent)
        }
        return root

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SOLICITUD_AGREGAR_META && resultCode == RESULT_OK) {
            val meta = data?.getStringExtra("meta")
            if (meta != null) {
                viewModel.metas.add(meta)
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, viewModel.metas)
            }
        }
    }

}


