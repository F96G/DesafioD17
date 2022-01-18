package com.munidigital.bc2201.challenge2

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    private val _mensajeList = MutableLiveData<MutableList<String>>()
    private val respuestas = Respuestas()
    var yaRespondi = true
    val mensajeList: LiveData<MutableList<String>> get() =_mensajeList

    init {
        refresh()
    }

    fun refresh(){
        _mensajeList.value = mutableListOf()
        respuestas.reload()
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
        _mensajeList.value?.let {
            if (it.size > 0) {
                for (i in 0..it.size - 1) {
                    lista.add(it.get(i))
                }
            }

        }
        lista.add(mensaje)

        _mensajeList.value = lista
    }
}