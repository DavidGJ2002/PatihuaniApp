package com.appsbig.patihuaniapp.alvaro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appsbig.patihuaniapp.R

class MetasActivities : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_metas_activities)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ... (Configurar el diseño, manejar interacciones)
        val botonListo: Button = findViewById(R.id.boton_listo)
        botonListo.setOnClickListener { view ->
            // Obtener el tipo de meta del RadioGroup
            val grupoTipoMeta: RadioGroup = findViewById(R.id.grupo_tipo_meta)
            val idTipoMetaSeleccionado: Int = grupoTipoMeta.getCheckedRadioButtonId()
            val botonTipoMetaSeleccionado: RadioButton = findViewById(idTipoMetaSeleccionado)
            val tipoDeMeta: String = botonTipoMetaSeleccionado.getText().toString()

            // Obtener el estado del recordatorio del Switch
            val interruptorRecordatorio: Switch = findViewById(R.id.interruptor_recordatorio)
            val tieneRecordatorio: Boolean = interruptorRecordatorio.isChecked()

            // Obtener la hora del recordatorio del TimePicker (si está activado)
            var horaRecordatorio = ""
            if (tieneRecordatorio) {
                val selectorHora: TimePicker = findViewById(R.id.selector_hora)
                selectorHora.setIs24HourView(true)
                val horaSeleccionada = selectorHora.getHour()
                val minutoSeleccionado = selectorHora.getMinute()
                horaRecordatorio = " - Recordatorio a las $horaSeleccionada:$minutoSeleccionado"

            } else {
                // ... (Extraer la hora del TimePicker, formatearla en un texto)

            }

            // Construir el texto de la meta
            val textoDeLaMeta = tipoDeMeta + horaRecordatorio

            val intentResultado: Intent = Intent()
            intentResultado.putExtra("TEXTO_DE_LA_META", textoDeLaMeta)
            setResult(RESULT_OK, intentResultado)
            finish()
        }
    }
}