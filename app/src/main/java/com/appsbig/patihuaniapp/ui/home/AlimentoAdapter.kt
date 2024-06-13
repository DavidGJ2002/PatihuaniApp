package com.appsbig.patihuaniapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.appsbig.patihuaniapp.R

class AlimentoAdapter(private val alimentos: List<Alimento>) : RecyclerView.Adapter<AlimentoAdapter.AlimentoViewHolder>() {

    class AlimentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlimentoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alimento, parent, false)
        return AlimentoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlimentoViewHolder, position: Int) {
        val alimento = alimentos[position]
        holder.textView.text = alimento.alimento  // Cambiado a 'alimento'
        // Cargar la imagen usando Glide
        Glide.with(holder.imageView.context)
            .load(alimento.imagen)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return alimentos.size
    }
}
