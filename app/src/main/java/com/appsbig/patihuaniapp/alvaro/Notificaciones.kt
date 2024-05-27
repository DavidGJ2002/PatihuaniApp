package com.appsbig.patihuaniapp.alvaro

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.appsbig.patihuaniapp.R

object Notificaciones {
    // Método para mostrar una notificación
fun mostrarNotificacion(contexto: Context, titulo: String, mensaje: String) {
    // Crear un ID único para el canal de notificación
    val channelId = "mi_canal_notificacion"

    // Crear el canal de notificación (solo necesario para versiones de Android Oreo y superiores)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            "Canal de Notificación",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        // Registrar el canal en el NotificationManager
        val notificationManager =
            contexto.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    // Construir la notificación usando NotificationCompat
    val builder = NotificationCompat.Builder(contexto, channelId)
        .setSmallIcon(R.drawable.pixeltech_removebg_preview)
        .setContentTitle(titulo)
        .setContentText(mensaje)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    // Mostrar la notificación
    with(NotificationManagerCompat.from(contexto)) {
        if (ActivityCompat.checkSelfPermission(
                contexto,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notify(0, builder.build())
    }
}
    fun NotificacionConActividadClicleable(
        contexto: Context?, titulo: String, mensaje: String,
        actividadDestino: Class<*>) {
        // Crear un ID único para el canal de notificación
        val channelId = "mi_canal_notificacion"

        // Crear el canal de notificación (solo necesario para versiones de Android Oreo y superiores)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Canal de Notificación",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            // Registrar el canal en el NotificationManager
            val notificationManager =
                contexto!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        val intent = Intent(contexto, actividadDestino)
        val pendingIntent = PendingIntent.getActivity(
            contexto,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Construir la notificación usando NotificationCompat
        val builder = NotificationCompat.Builder(contexto!!, channelId)
            .setSmallIcon(R.drawable.pixeltech_removebg_preview)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Mostrar la notificación
        with(NotificationManagerCompat.from(contexto)) {
            if (ActivityCompat.checkSelfPermission(
                    contexto,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(1, builder.build())
        }
    }
}