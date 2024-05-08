package com.appsbig.patihuaniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.google.firebase.database.FirebaseDatabase

class Registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)
        val rNombre = findViewById<EditText>(R.id.nombreR)
        val rApellidos = findViewById<EditText>(R.id.apellidosR)
        val rCorreo = findViewById<EditText>(R.id.correoR)
        val rContrasena = findViewById<EditText>(R.id.contrasenaR)
        val btnRegistrar = findViewById<Button>(R.id.botonRegistra)
        val opIS = findViewById<TextView>(R.id.opcionIS2)

        btnRegistrar.setOnClickListener {
            val nombreU = rNombre.text.toString()
            val apellidosU = rApellidos.text.toString()
            val correoU = rCorreo.text.toString()
            val contrasenaU = rContrasena.text.toString()

            val bd = FirebaseDatabase.getInstance()
            val usuarioRef = bd.getReference("usuarios")
            val usuarioid = usuarioRef.push().key
            val usuario = Usuario(nombreU,apellidosU,correoU,contrasenaU)

            usuarioid?.let {
                usuarioRef.child(it).setValue(usuario)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                        println("verificar llenado de campos: ${it.message}")
                    }
            }

            }
        opIS.setOnClickListener {
            val intentIS = Intent(this,IniciarSesion::class.java)
            startActivity(intentIS)
        }

    }
}