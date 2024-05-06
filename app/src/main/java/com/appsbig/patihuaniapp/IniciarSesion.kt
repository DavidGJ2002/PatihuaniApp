    package com.appsbig.patihuaniapp

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.TextView
    import android.widget.Toast

    class IniciarSesion : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_iniciarsesion)

            val btnSiguiente = findViewById<Button>(R.id.botonIS)
            val ptUsuario = findViewById<EditText>(R.id.nombre)
            val ptContrasena = findViewById<EditText>(R.id.contrasena)
            val opRegistrar = findViewById<TextView>(R.id.opcionRegistrate)

            btnSiguiente.setOnClickListener {
                val usuario = ptUsuario.text.toString()
                val contrasena = ptContrasena.text.toString()

                if (usuario == "david" && contrasena == "123") {
                    val intent = Intent(this, Bienvenido::class.java)
                    intent.putExtra("usuario", usuario)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                }
            }

            opRegistrar.setOnClickListener {
                val intentRegistraUs = Intent(this,Registrar::class.java)
                startActivity(intentRegistraUs)
            }
        }
    }