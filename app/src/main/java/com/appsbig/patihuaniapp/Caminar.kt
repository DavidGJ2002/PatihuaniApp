package com.appsbig.patihuaniapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.appsbig.patihuaniapp.alvaro.Notificaciones
import com.appsbig.patihuaniapp.alvaro.ServicioAlarma
import com.appsbig.patihuaniapp.alvaro.Whatsapp

class Caminar : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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
            "correr" -> {
                // Se seleccionó el botón de nadar, cargar el XML correspondiente
                setContentView(R.layout.activity_correr)
                // Agregar lógica específica para el botón de nadar si es necesario
            }
            "saltar" -> {
                // Se seleccionó el botón de nadar, cargar el XML correspondiente
                setContentView(R.layout.activity_saltar)
                // Agregar lógica específica para el botón de nadar si es necesario
            }
            // Agregar casos para los otros botones si es necesario...
            else -> {
                finish()
            }
        }

        // Agregar cualquier otra lógica común a todas las actividades aquí...
    }
}
