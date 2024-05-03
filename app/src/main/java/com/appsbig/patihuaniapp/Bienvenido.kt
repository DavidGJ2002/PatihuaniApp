package com.appsbig.patihuaniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Bienvenido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenido)

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)

        val nombreUsuario = intent.getStringExtra("usuario")

        tvBienvenida.append(" "+ nombreUsuario)
    }
}