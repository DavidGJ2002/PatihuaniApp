package com.appsbig.patihuaniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.appsbig.patihuaniapp.ui.gallery.GalleryFragment
import com.appsbig.patihuaniapp.ui.gallery.Usuario
import com.google.firebase.database.*

class IniciarSesion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciarsesion)

        val btnSiguiente = findViewById<Button>(R.id.botonIS)
        val ptUsuario = findViewById<EditText>(R.id.nombre)
        val ptContrasena = findViewById<EditText>(R.id.contrasena)
        val opRegistrar = findViewById<TextView>(R.id.opcionRegistrate)
        val databaseRef = FirebaseDatabase.getInstance().getReference("usuarios")

        btnSiguiente.setOnClickListener {
            val correo = ptUsuario.text.toString()
            val contrasena = ptContrasena.text.toString()
            databaseRef.orderByChild("correo").equalTo(correo).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var actividadIniciada = false
                    if (snapshot.exists()) {
                        snapshot.children.forEach { child ->
                            val usuario = child.getValue(Usuario::class.java)
                            if (usuario != null && usuario.contrasena == contrasena && !actividadIniciada) {
                                val intent = Intent(this@IniciarSesion, Bienvenido::class.java).apply {
                                    putExtra("usuario", usuario.nombre)
                                }
                                startActivity(intent)

                                actividadIniciada = true  // Marcar que se ha iniciado una actividad
                            }
                        }
                        if (!actividadIniciada) {
                            Toast.makeText(this@IniciarSesion, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@IniciarSesion, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }


                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@IniciarSesion, "Error al acceder a la base de datos: ${error.message}", Toast.LENGTH_LONG).show()
                }
            })
        }

        opRegistrar.setOnClickListener {
            startActivity(Intent(this, Registrar::class.java))
        }
    }
}
