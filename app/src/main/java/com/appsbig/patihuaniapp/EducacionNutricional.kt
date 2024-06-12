package com.appsbig.patihuaniapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EducacionNutricional : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_educacion_nutricional)
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

        // Configurar el botón de regreso
        backButton.setOnClickListener {
            val intent = Intent(this, Bienvenido::class.java)
            startActivity(intent)
            finish()
        }

        // Agregar listeners a los botones
        button8.setOnClickListener {
            val intent = Intent(this, Caminar::class.java)
            intent.putExtra("boton_seleccionado", "etiquetado")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }

        button9.setOnClickListener {
            val intent = Intent(this, Caminar::class.java)
            intent.putExtra("boton_seleccionado", "hipertencion")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }

        button10.setOnClickListener {
            val intent = Intent(this, Caminar::class.java)
            intent.putExtra("boton_seleccionado", "lacteos")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }

        button11.setOnClickListener {
            val intent = Intent(this, Caminar::class.java)
            intent.putExtra("boton_seleccionado", "pan")
            intent.putExtra("return_activity", "EducacionNutricional")
            startActivity(intent)
        }
    }
}