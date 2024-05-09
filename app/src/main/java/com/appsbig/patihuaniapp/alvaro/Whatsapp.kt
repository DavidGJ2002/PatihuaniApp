package com.appsbig.patihuaniapp.alvaro
import android.content.Context
import android.content.Intent
import android.widget.Toast
object Whatsapp {
    fun compartirMensajeTexto(contexto: Context, mensaje: String) {
        val intentEnviar = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mensaje)
            type = "text/plain"
            setPackage("com.whatsapp")
        }

        try {
            contexto.startActivity(intentEnviar)
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(contexto, "WhatsApp no está instalado.", Toast.LENGTH_SHORT).show()
        }
    }
}