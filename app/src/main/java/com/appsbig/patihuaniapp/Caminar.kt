package com.appsbig.patihuaniapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.appsbig.patihuaniapp.alvaro.Notificaciones
import com.appsbig.patihuaniapp.alvaro.ServicioAlarma
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class Caminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caminar)
        val imagen = findViewById<ImageView>(R.id.compartirCaminar) // define la variable imagen como un ImageView
        val stringMsj="checa lo que la aplicacion Patihuamiapp me recomendo sobre caminar aqui: https://play.google.com/store/apps/details?id=com.appsbig.patihuaapp&hl=es_MX&gl=US" // define la variable stringMsj como un String
        imagen.setOnClickListener {// cuando se hace click en la imagen
            //ServicioAlarma.configurarAlarma(this,1,10_000L)// 10segundos
            //Notificaciones.mostrarNotificacion(this,"amar","amor oir dolor")
            Whatsapp.compartirMensajeTexto(this, stringMsj) // llama a la funcion compartirMensajeTexto de la clase Whatsapp
        }

    }
}