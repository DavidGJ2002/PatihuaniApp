package com.appsbig.patihuaniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.appsbig.patihuaniapp.ui.gallery.Usuario
import com.google.firebase.database.*

class Registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

            if (nombreU.isEmpty() || apellidosU.isEmpty() || correoU.isEmpty() || contrasenaU.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bd = FirebaseDatabase.getInstance()
            val usuarioRef = bd.getReference("usuarios")

            val query = usuarioRef.orderByChild("correo").equalTo(correoU)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Toast.makeText(this@Registrar, "Este correo ya está registrado", Toast.LENGTH_SHORT).show()
                    } else {
                        val usuarioid = usuarioRef.push().key
                        val usuario = Usuario(nombreU, apellidosU, correoU, contrasenaU, null, null, null, null, null, null, null)

                        usuarioid?.let {
                            usuarioRef.child(it).setValue(usuario)
                                .addOnSuccessListener {
                                    Toast.makeText(this@Registrar, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this@Registrar, "Error al registrar", Toast.LENGTH_SHORT).show()
                                    println("verificar llenado de campos: ${it.message}")
                                }
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@Registrar, "Error al verificar el correo: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        opIS.setOnClickListener {
            val intentIS = Intent(this, IniciarSesion::class.java)
            startActivity(intentIS)
        }
    }
}
