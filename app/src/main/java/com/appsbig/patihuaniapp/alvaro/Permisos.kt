package com.appsbig.patihuaniapp.alvaro

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


object Permisos {
    //funcion para checar permisos de locacion
     fun checarPermisoUbicacion(contexto: Context):Boolean{
         return ContextCompat.checkSelfPermission(contexto,
             Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
     }
    
}