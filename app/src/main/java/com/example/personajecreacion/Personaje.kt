package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable
import kotlin.random.Random

data class Personaje(
    private var nombre: String,
    private var estadoVital: String,
    private var raza: String,
    private var clase: String,

    ): Parcelable {

    private var experiencia : Int
    private var nivel : Int
    private var salud : Int
    private var ataque : Int
    private var defensa : Int

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {

    }

    init {
        salud = 100
        ataque = 10
        experiencia = 0
        nivel = 1
        defensa = 4
    }

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getEstadoVital(): String {
        return estadoVital
    }

    fun setEstadoVital(estadoVital: String) {
        this.estadoVital = estadoVital
    }

    fun getRaza(): String {
        return raza
    }

    fun setRaza(raza: String) {
        this.raza = raza
    }

    fun getClase(): String {
        return clase
    }

    fun setClase(clase: String) {
        this.clase = clase
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(estadoVital)
        parcel.writeString(raza)
        parcel.writeString(clase)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Personaje> {
        override fun createFromParcel(parcel: Parcel): Personaje {
            return Personaje(parcel)
        }

        override fun newArray(size: Int): Array<Personaje?> {
            return arrayOfNulls(size)
        }
    }
}