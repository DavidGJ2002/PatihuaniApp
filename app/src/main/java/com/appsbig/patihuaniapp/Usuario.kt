package com.appsbig.patihuaniapp

class Usuario(val nombre: String, val apellidos: String, val contrasena: String, val correo: String) {
    constructor() : this("","","","")
}