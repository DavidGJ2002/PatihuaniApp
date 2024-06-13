package com.appsbig.patihuaniapp.alvaro

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appsbig.patihuaniapp.R

class ProgresoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_progreso2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sharedPreferences = getSharedPreferences("goal_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

// Saving a new goal
        editor.putString("goal_water_intake", "Water Intake - 8 glasses")
        editor.apply()

// Updating progress for a goal
        editor.putString("progress_water_intake", "Water Intake - 5 glasses")
        editor.putLong("last_update_water_intake", System.currentTimeMillis())
        editor.apply()
    }
}