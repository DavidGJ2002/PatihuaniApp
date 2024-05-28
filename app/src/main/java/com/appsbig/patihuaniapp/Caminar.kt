package com.appsbig.patihuaniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Caminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caminar)

        val botonSeleccionado = intent.getStringExtra("boton_seleccionado")
        val backButton = findViewById<Button>(R.id.button_back)

        // Agregar OnClickListener al botón de regreso
        backButton.setOnClickListener {
            // Crear un Intent para volver a la clase de bienvenida
            val intent = Intent(this, Bienvenido::class.java)
            // Iniciar la actividad de bienvenida
            startActivity(intent)
            // Finalizar esta actividad para que el usuario no pueda volver atrás
            finish()
        }
        // Cargar el XML correspondiente según el botón seleccionado
        when (botonSeleccionado) {
            "caminar" -> {
                // Se seleccionó el botón de caminar, cargar el XML correspondiente
                setContentView(R.layout.activity_caminar)
                // Agregar lógica específica para el botón de caminar si es necesario
            }
            "nadar" -> {
                // Se seleccionó el botón de nadar, cargar el XML correspondiente
                setContentView(R.layout.activity_nadar)
                // Agregar lógica específica para el botón de nadar si es necesario
            }
            "saltar" -> {
                // Se seleccionó el botón de nadar, cargar el XML correspondiente
                setContentView(R.layout.activity_saltar)
                // Agregar lógica específica para el botón de nadar si es necesario
            }
            "correr" -> {
                // Se seleccionó el botón de nadar, cargar el XML correspondiente
                setContentView(R.layout.activity_correr)
                // Agregar lógica específica para el botón de nadar si es necesario
            }
            // Si no se seleccionó ningún botón válido, cerrar la actividad
            else -> {
                finish()
            }
        }
    }
}