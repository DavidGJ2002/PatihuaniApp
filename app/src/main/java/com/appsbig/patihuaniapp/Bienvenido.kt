package com.appsbig.patihuaniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class Bienvenido : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenido)

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)

        val nombreUsuario = intent.getStringExtra("usuario")

        tvBienvenida.append(" "+ nombreUsuario)

        Handler().postDelayed({
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}