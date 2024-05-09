package com.appsbig.patihuaniapp.alvaro

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object ServicioAlarma {
    fun configurarAlarma(contexto: Context, idAlarma: Int, tiempoDisparoMilisegundos: Long) {
        val administradorAlarma = contexto.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intento = Intent(contexto, ReceptorAlarma::class.java).apply {
            putExtra("idAlarma", idAlarma)
        }

        val intentoPendiente = PendingIntent.getBroadcast(contexto, idAlarma, intento, PendingIntent.FLAG_UPDATE_CURRENT)

        administradorAlarma.setExact(AlarmManager.RTC_WAKEUP, tiempoDisparoMilisegundos, intentoPendiente)
    }
}