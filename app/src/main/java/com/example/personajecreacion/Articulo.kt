package com.example.personajecreacion

data class Articulo (private var nombre: String,
                     private var peso: Int,
                     private var tipo: TipoArt?,
                     private var url: String,
                     private var unidades: Int,
                     private var precio: Int){
    private var id:Int = 0
    fun getNombre(): String {
        return nombre
    }
    fun getPeso(): Int {
        return peso
    }
    fun getTipo(): TipoArt? {
        return tipo
    }
    fun getUrl(): String {
        return url
    }
    fun getUnidades(): Int {
        return unidades
    }
    fun setId(id:Int){
        this.id=id
    }

    enum class TipoArt (val tipo: String) {
        ARMA("arma"),
        PROTECCION ("proteccion"),
        OBJETO ("objeto"),
        ORO("oro")
    }
}
