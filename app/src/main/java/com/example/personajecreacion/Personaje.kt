package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable

data class Personaje(
    private var nombre: String?,
    private var estadoVital: String?,
    private var raza: String?,
    private var clase: String?,
    private var mochila: Mochila?
) : Parcelable {

    private var experiencia: Int
    private var nivel: Int
    private var salud: Int
    private var ataque: Int
    private var defensa: Int

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Mochila::class.java.classLoader),
    ) {
        experiencia = parcel.readInt()
        nivel = parcel.readInt()
        salud = parcel.readInt()
        ataque = parcel.readInt()
        defensa = parcel.readInt()
    }

    init {
        salud = 100
        ataque = 10
        experiencia = 0
        nivel = 1
        defensa = 4
    }

    fun getNombre(): String? {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getEstadoVital(): String? {
        return estadoVital
    }

    fun setEstadoVital(estadoVital: String) {
        this.estadoVital = estadoVital
    }

    fun getRaza(): String? {
        return raza
    }

    fun setRaza(raza: String) {
        this.raza = raza
    }

    fun getClase(): String? {
        return clase
    }

    fun setClase(clase: String) {
        this.clase = clase
    }

    fun getMochila(): Mochila? {
        return this.mochila
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(estadoVital)
        parcel.writeString(raza)
        parcel.writeString(clase)
        parcel.writeParcelable(mochila, flags)
        parcel.writeInt(experiencia)
        parcel.writeInt(nivel)
        parcel.writeInt(salud)
        parcel.writeInt(ataque)
        parcel.writeInt(defensa)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Personaje(nombre=$nombre, estadoVital=$estadoVital, raza=$raza, clase=$clase, mochila=$mochila, experiencia=$experiencia, nivel=$nivel, salud=$salud, ataque=$ataque, defensa=$defensa)"
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