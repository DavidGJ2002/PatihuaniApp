package com.appsbig.patihuaniapp.alvaro

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.appsbig.patihuaniapp.Caminar
import com.appsbig.patihuaniapp.DietaDiaria
import com.appsbig.patihuaniapp.Noticia

class ReceptorAlarma : BroadcastReceiver() {
    override fun onReceive(contexto: Context?, intento: Intent?) {
        // Obtener el ID de la alarma del intento
        val idAlarma = intento?.getIntExtra("idAlarma", 0) ?: return

        // Determinar qué hacer en base al ID de la alarma
        val actividadObjetivo = when (idAlarma) {
            1 -> Noticia::class.java
            2 -> Caminar::class.java
            3 ->DietaDiaria::class.java
            else -> null
        }
        if (idAlarma == 4)Notificaciones.NotificacionConActividadClicleable(contexto,"Nueve dieta","Tenemos una dieta diaria Hoy", DietaDiaria::class.java)
        // Crear un intento para iniciar la actividad objetivo
        val intentoActividad = Intent(contexto, actividadObjetivo)

        // Agregar la bandera FLAG_ACTIVITY_NEW_TASK para iniciar la actividad fuera de una actividad existente
        intentoActividad.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        // Iniciar la actividad objetivo
        contexto?.startActivity(intentoActividad)
    }
}