package com.appsbig.patihuaniapp.alvaro

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import java.util.Locale

object ServicioLocacion {
    fun obtenerUbicacion(contexto: Context): String {

    var direciones=""
    // Obtener la ubicación del dispositivo
        val clienteFusedLocation = LocationServices.getFusedLocationProviderClient(contexto)
        clienteFusedLocation.lastLocation
            .addOnSuccessListener { ubicacion ->
                if (ubicacion != null) {
                    // Obtener la dirección a partir de la ubicación
                    val geocoder = Geocoder(contexto, Locale.getDefault())
                    direciones = geocoder.getFromLocation(ubicacion.latitude, ubicacion.longitude, 1)!!
                        .firstOrNull()?.getAddressLine(0) ?: ""

                }
            }.addOnFailureListener { e ->
                // Manejar errores
                e.printStackTrace()

            }
        return direciones
    }
}