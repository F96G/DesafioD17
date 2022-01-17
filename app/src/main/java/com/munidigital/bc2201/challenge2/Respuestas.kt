package com.munidigital.bc2201.challenge2

class Respuestas {
    val listaRespuestas = arrayListOf<String>()

    init {
        listaRespuestas.add("Si")
        listaRespuestas.add("No")
        listaRespuestas.add("Pregunta de nuevo")
        listaRespuestas.add("Es muy probable")
        listaRespuestas.add("No lo creo")
        listaRespuestas.add("No sé \uD83D\uDE41")
        listaRespuestas.add("Tal vez")
    }

    fun getRespuesta():String{
        return listaRespuestas.random()
    }
}