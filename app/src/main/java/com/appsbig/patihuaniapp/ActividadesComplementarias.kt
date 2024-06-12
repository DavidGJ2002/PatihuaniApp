package com.appsbig.patihuaniapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActividadesComplementarias : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividades_complementarias)

        // Asegúrate de que existe un elemento con ID 'main' en tu layout activity_actividades_complementarias
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Busca los botones en el layout
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
            intent.putExtra("boton_seleccionado", "caminar")
            intent.putExtra("return_activity", "ActividadesComplementarias")
            startActivity(intent)
        }

        button9.setOnClickListener {
            val intent = Intent(this, Caminar::class.java)
            intent.putExtra("boton_seleccionado", "correr")
            intent.putExtra("return_activity", "ActividadesComplementarias")
            startActivity(intent)
        }

        button10.setOnClickListener {
            val intent = Intent(this, Caminar::class.java)
            intent.putExtra("boton_seleccionado", "nadar")
            intent.putExtra("return_activity", "ActividadesComplementarias")
            startActivity(intent)
        }

        button11.setOnClickListener {
            val intent = Intent(this, Caminar::class.java)
            intent.putExtra("boton_seleccionado", "saltar")
            intent.putExtra("return_activity", "ActividadesComplementarias")
            startActivity(intent)
        }
    }
}
