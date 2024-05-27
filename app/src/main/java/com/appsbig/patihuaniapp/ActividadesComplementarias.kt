package com.appsbig.patihuaniapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActividadesComplementarias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividades_complementarias)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button10 = findViewById<Button>(R.id.button10)
        val button11 = findViewById<Button>(R.id.button11)
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
        // Agregar listeners a los botones
        button8.setOnClickListener {
            // Crear un Intent para la actividad correspondiente
            val intent = Intent(this, Caminar::class.java)
            // Pasar información sobre qué botón se seleccionó
            intent.putExtra("boton_seleccionado", "caminar")
            // Iniciar la nueva actividad
            startActivity(intent)

        }

        button9.setOnClickListener {
            // Crear un Intent para la actividad correspondiente
            val intent = Intent(this, Caminar::class.java)
            // Pasar información sobre qué botón se seleccionó
            intent.putExtra("boton_seleccionado", "correr")
            // Iniciar la nueva actividad
            startActivity(intent)
        }

        button10.setOnClickListener {
            // Crear un Intent para la actividad correspondiente
            val intent = Intent(this, Caminar::class.java)
            // Pasar información sobre qué botón se seleccionó
            intent.putExtra("boton_seleccionado", "nadar")
            // Iniciar la nueva actividad
            startActivity(intent)
        }

        button11.setOnClickListener {
            // Crear un Intent para la actividad correspondiente
            val intent = Intent(this, Caminar::class.java)
            // Pasar información sobre qué botón se seleccionó
            intent.putExtra("boton_seleccionado", "Saltar")
            // Iniciar la nueva actividad
            startActivity(intent)
        }
    }
}