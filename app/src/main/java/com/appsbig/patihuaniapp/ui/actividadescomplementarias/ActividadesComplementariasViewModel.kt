package com.appsbig.patihuaniapp.ui.actividadescomplementarias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActividadesComplementariasViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value ="Bienvenido la presentacion de la aplicacion del equipo 3 de desarrollo movil. Eliga una de las categorias en el menu lateral o cierre la aplicacion accesiendo a las configuraciones"
    }
    val text: LiveData<String> = _text
}