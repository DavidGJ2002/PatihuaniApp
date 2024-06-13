package com.appsbig.patihuaniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class ActividadesComplementarias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_complementarias)


    }

    private fun abrirActividad(botonSeleccionado: String) {
        val intent = Intent(this, Caminar::class.java)
        intent.putExtra("boton_seleccionado", botonSeleccionado)
        intent.putExtra("return_activity", "ActividadesComplementarias")
        startActivity(intent)
    }
}


