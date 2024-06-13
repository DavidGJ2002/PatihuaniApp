package com.appsbig.patihuaniapp.ui.gallery

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Usuario(
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val contrasena: String,
    var fotoPerfil: Uri? = null,
    var fechaNacimiento: String? = null,
    var peso: Double? = null,
    var altura: Double? = null,
    var opcionLista: String? = null,
    var otraOpcionLista: String? = null,
    var objetivo: String? = null,
    var ubicacion: String? = null,
    var infoExtra: String? = null,
    var caloriasDiarias: Double? = null,
    var proteinasDiarias: Double? = null,
    var carbohidratosDiarios: Double? = null
) {
    // Constructor secundario para inicialización con solo los datos obligatorios
    constructor(nombre: String, apellidos: String, correo: String, contrasena: String) : this(nombre, apellidos, correo, contrasena, null, null, null, null, null, null, null, null, null)

    // Constructor vacío
    constructor() : this("", "", "", "")

    // Método para actualizar el perfil
    fun actualizarPerfil(
        fotoPerfil: String?,
        fechaNacimiento: String?,
        peso: Double?,
        altura: Double?,
        opcionLista: String?,
        otraOpcionLista: String?,
        objetivo: String?,
        ubicacion: String?,
        infoExtra: String?,
        caloriasDiarias: Double?,
        proteinasDiarias: Double?,
        carbohidratosDiarios: Double?
    ) {
        this.fotoPerfil = if (fotoPerfil != null) Uri.parse(fotoPerfil) else null
        this.fechaNacimiento = fechaNacimiento
        this.peso = peso
        this.altura = altura
        this.opcionLista = opcionLista
        this.otraOpcionLista = otraOpcionLista
        this.objetivo = objetivo
        this.ubicacion = ubicacion
        this.infoExtra = infoExtra
        this.caloriasDiarias = caloriasDiarias
        this.proteinasDiarias = proteinasDiarias
        this.carbohidratosDiarios = carbohidratosDiarios

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios").child(correo.replace(".", "_"))

        myRef.setValue(this).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Perfil actualizado exitosamente en Firebase")
            } else {
                println("Error al actualizar el perfil en Firebase: ${task.exception}")
            }
        }
    }

    // Método para calcular la edad a partir de la fecha de nacimiento
    fun calcularEdad(): Int? {
        if (fechaNacimiento == null) return null
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val birthDate = dateFormat.parse(fechaNacimiento) ?: return null
        val today = Calendar.getInstance()
        val birthDay = Calendar.getInstance().apply { time = birthDate }
        var age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }
}
