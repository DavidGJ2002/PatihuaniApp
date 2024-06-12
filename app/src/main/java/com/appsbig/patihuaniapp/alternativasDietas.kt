package com.appsbig.patihuaniapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class alternativasDietas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alternativas_dietas)
        val backButton = findViewById<Button>(R.id.button_back)
        backButton.setOnClickListener {
            val intent = Intent(this, Bienvenido::class.java)
            startActivity(intent)
            finish()
        }

        // Buscar los botones en el layout
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button10 = findViewById<Button>(R.id.button10)
        val button11 = findViewById<Button>(R.id.button11)

        // Agregar listeners a los botones
        button8.setOnClickListener {
            abrirActividad("Dieta1")
        }

        button9.setOnClickListener {
            abrirActividad("Dieta2")
        }

        button10.setOnClickListener {
            abrirActividad("Alternativa1")
        }

        button11.setOnClickListener {
            abrirActividad("Alternativa2")
        }
    }

    private fun abrirActividad(botonSeleccionado: String) {
        val intent = Intent(this, Caminar::class.java)
        intent.putExtra("boton_seleccionado", botonSeleccionado)
        intent.putExtra("return_activity", "alternativasDietas")
        startActivity(intent)
    }
}