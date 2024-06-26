package com.appsbig.patihuaniapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class DietaDiaria : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dieta_diaria)
        val imagen = findViewById<ImageView>(R.id.dieta_diaria_whats) // define la variable imagen como un ImageView
        val stringMsj="Esta app es un milagro me recomendo  una dieta hoy aqui para que te recomiende tambien: https://play.google.com/store/apps/details?id=com.appsbig.patihuaapp&hl=es_MX&gl=US" // define la variable stringMsj como un String
        imagen.setOnClickListener {// cuando se hace click en la imagen
            Whatsapp.compartirMensajeTexto(this, stringMsj)
        // llama a la funcion compartirMensajeTexto de la clase Whatsapp
        }
        setContentView(R.layout.activity_alternativas_dietas)
        val backButton = findViewById<Button>(R.id.back_DietaDiaria)
        backButton.setOnClickListener {
            val intent = Intent(this, Navegacion::class.java)
            startActivity(intent)
            finish()
        }
    }
}