package com.appsbig.patihuaniapp.alvaro

import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.appsbig.patihuaniapp.IniciarSesion
import com.appsbig.patihuaniapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val cerrarSesionConfig = findPreference<Preference>("ceerar_sesion")
        cerrarSesionConfig?.setOnPreferenceClickListener {
            val intent = Intent(context, IniciarSesion::class.java)
            startActivity(intent)
            activity?.finish()
            true
        }
    }
}