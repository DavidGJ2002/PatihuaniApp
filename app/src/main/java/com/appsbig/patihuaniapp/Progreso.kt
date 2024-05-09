package com.appsbig.patihuaniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class Progreso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progreso)
        val imagen = findViewById<ImageView>(R.id.progreso_whats) // define la variable imagen como un ImageView
        val stringMsj="Esta app es un milagro  estoy perdiendo peso y me estan dando mi crogreso dieta hoy aqui para que te recomiende tambien: https://play.google.com/store/apps/details?id=com.appsbig.patihuaapp&hl=es_MX&gl=US" // define la variable stringMsj como un String
        imagen.setOnClickListener {// cuando se hace click en la imagen
            Whatsapp.compartirMensajeTexto(this, stringMsj)
            // llama a la funcion compartirMensajeTexto de la clase Whatsapp
        }
    }
}