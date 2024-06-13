package com.appsbig.patihuaniapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DietaNueva : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dieta_nueva)
        setContentView(R.layout.activity_alternativas_dietas)
        val backButton = findViewById<Button>(R.id.back_DietaNueva)
        backButton.setOnClickListener {
            val intent = Intent(this, DietaDiaria::class.java)
            startActivity(intent)
            finish()
        }
    }
}