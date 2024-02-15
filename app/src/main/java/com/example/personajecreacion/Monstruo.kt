package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable

data class Monstruo(
    private var nombre: String?,
    private var nivel: Int
) :Parcelable{
    private var salud: Int = 0
    private var ataque: Int = 0
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
        calcularSalud()
        calcularAtaque()
    }



    fun getNombre(): String? {
        return nombre
    }
    fun setNombre(nuevoNombre:String) {
        nombre = nuevoNombre
    }
    fun getNivel(): Int {
        return nivel
    }
    fun setNivel(nuevoNivel:Int) {
        nivel = nuevoNivel
    }
    fun getSalud(): Int {
        return salud
    }
    fun setSalud(nuevaSalud: Int) {
        salud = nuevaSalud
    }
    fun getAtaque(): Int {
        return ataque
    }
    fun setAtaque(nuevoAtaque: Int) {
        ataque = nuevoAtaque
    }
    private fun calcularSalud() {
        salud = when (nivel) {
            1 -> 100
            2 -> 125
            3 -> 150
            4 -> 200
            5 -> 250
            6 -> 350
            7 -> 400
            8 -> 600
            9 -> 800
            10 -> 1000
            else -> 100 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }
    private fun calcularAtaque() {
        ataque = when (nivel) {
            1 -> 5
            2 -> 10
            3 -> 15
            4 -> 20
            5 -> 50
            6 -> 60
            7 -> 120
            8 -> 200
            9 -> 350
            10 -> 400
            else -> 5 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }
    override fun toString(): String {
        return "Monstruo: Nombre: $nombre, Nivel: $nivel, Salud: $salud, Ataque: $ataque"
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(nivel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Monstruo> {
        override fun createFromParcel(parcel: Parcel): Monstruo {
            return Monstruo(parcel)
        }

        override fun newArray(size: Int): Array<Monstruo?> {
            return arrayOfNulls(size)
        }
    }
}