package com.appsbig.patihuaniapp.alvaro

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


object Permisos {
    //funcion para checar permisos de locacion
     fun checarPermisoUbicacion(contexto: Context):Boolean{
         return ContextCompat.checkSelfPermission(contexto,
             Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
     }
    // funcion para checar permisos de almacen
    fun checarPermisoAlmacen(contexto: Context):Boolean{
        return ContextCompat.checkSelfPermission(contexto,
            Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
    }
    //funcion para checar permisos de camara
    fun checarPermisoCamara(contexto: Context):Boolean{
        return ContextCompat.checkSelfPermission(contexto,
            Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
    }

    //funcion para checar permisos de internet
    fun checarPermisoInternet(contexto: Context):Boolean{
        return ContextCompat.checkSelfPermission(contexto,
            Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED
    }

    //funcion para pedir permisos de locacion


}