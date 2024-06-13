package com.appsbig.patihuaniapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appsbig.patihuaniapp.Bienvenido
import com.appsbig.patihuaniapp.R

class Soporte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_soporte)
        val buttonHelp = findViewById<Button>(R.id.backSoporte)
        buttonHelp.setOnClickListener {
            val intent = Intent(this, Bienvenido::class.java)
            startActivity(intent)
        }

    }
}