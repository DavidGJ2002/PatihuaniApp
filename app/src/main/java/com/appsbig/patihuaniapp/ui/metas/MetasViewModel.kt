package com.appsbig.patihuaniapp.ui.metas

import androidx.lifecycle.ViewModel

class MetasViewModel : ViewModel() {
 var metas = mutableListOf<String>()
    fun addMeta(meta: String) {
        metas.add(meta)
    }

}