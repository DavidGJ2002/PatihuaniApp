package com.appsbig.patihuaniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class Noticia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticia)
        val imagen = findViewById<ImageView>(R.id.noticia_whatd) // define la variable imagen como un ImageView
        val stringMsj="me remomendo una noticia que me cambia ra la vida aqui para que te recomiende tambien: https://play.google.com/store/apps/details?id=com.appsbig.patihuaapp&hl=es_MX&gl=US" // define la variable stringMsj como un String
        imagen.setOnClickListener {// cuando se hace click en la imagen
            Whatsapp.compartirMensajeTexto(this, stringMsj)
            // llama a la funcion compartirMensajeTexto de la clase Whatsapp
        }
    }
}