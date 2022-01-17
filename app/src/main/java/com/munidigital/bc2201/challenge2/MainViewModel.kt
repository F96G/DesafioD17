package com.munidigital.bc2201.challenge2

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    private val _mensajeList = MutableLiveData<MutableList<String>>()
    private val list = mutableListOf<String>()
    private val respuestas = Respuestas()
    var yaRespondi = true
    val mensajeList: LiveData<MutableList<String>> get() =_mensajeList

    init {
        _mensajeList.value = mutableListOf<String>()
    }

    fun respond(){
        Handler(Looper.getMainLooper()).postDelayed({
            addMensaje(respuestas.getRespuesta())
            yaRespondi = true
        }, 2000)

    }

    fun sendMensaje(mensaje:String){
        addMensaje(mensaje)
        yaRespondi = false
        respond()
    }

    private fun addMensaje(mensaje:String){
        val lista = mutableListOf<String>()
        if (list.size > 0) {
            for (i in 0..list.size - 1) {
                lista.add(list.get(i))
            }
        }
        list.add(mensaje)
        lista.add(mensaje)

        _mensajeList.value = lista
    }
}