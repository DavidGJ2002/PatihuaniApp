package com.appsbig.patihuaniapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsbig.patihuaniapp.databinding.FragmentHomeBinding
import com.google.firebase.database.*
import com.appsbig.patihuaniapp.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlimentoAdapter
    private val databaseReference = FirebaseDatabase.getInstance().getReference("dietas")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        fetchAlimentos()

        return root
    }

    private fun fetchAlimentos() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val alimentosList = mutableListOf<Alimento>()
                for (snapshot in dataSnapshot.children) {
                    val alimento = snapshot.getValue(Alimento::class.java)
                    if (alimento != null) {
                        alimentosList.add(alimento)
                    }
                }
                // Seleccionar aleatoriamente 15 elementos
                val selectedAlimentos = alimentosList.shuffled().take(15)
                adapter = AlimentoAdapter(selectedAlimentos)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle possible errors.
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}