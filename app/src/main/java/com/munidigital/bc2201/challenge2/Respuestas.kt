package com.munidigital.bc2201.challenge2

class Respuestas {
    private val listaRespuestas = arrayListOf<String>()
    private var cantidadRespuestas = 1
    init {
        listaRespuestas.add("Si")
        listaRespuestas.add("No")
        listaRespuestas.add("Es muy probable")
        listaRespuestas.add("No lo creo")
        listaRespuestas.add("No sé \uD83D\uDE41")
        listaRespuestas.add("Tal vez")
    }

    fun reload(){
        cantidadRespuestas = 1
    }

    fun getRespuesta():String{
        if (cantidadRespuestas<3) {
            cantidadRespuestas += 1
            return listaRespuestas.random()
        }else if (cantidadRespuestas == 3) {
            cantidadRespuestas += 1
            return listaRespuestas.random() + "\nEspero que os haya iluminado"
        }else
            return "Gracias vuelva pronto"
    }
}