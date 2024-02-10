package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable

data class Articulo(
    private var nombre: String?,
    private var peso: Int,
    private var tipo: TipoArt?,
    private var imagen: String?,
    private var unidades: Int,
    private var precio: Int
) : Parcelable {
    private var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        TipoArt.valueOf(parcel.readString() ?: ""),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
        id = parcel.readInt()
    }

    fun getPrecio(): Int {
        return precio
    }

    fun sumaPrecio(valorASumar: Int) {
        precio += valorASumar
    }

    fun getNombre(): String? {
        return nombre
    }

    fun getPeso(): Int {
        return peso
    }

    fun getTipo(): TipoArt? {
        return tipo
    }

    fun getImagen(): String? {
        return imagen
    }

    fun getUnidades(): Int {
        return unidades
    }

    fun setId(id: Int) {
        this.id = id
    }

    enum class TipoArt(val tipo: String) {
        ARMA("arma"),
        PROTECCION("proteccion"),
        OBJETO("objeto"),
        ORO("oro")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(peso)
        parcel.writeString(tipo?.name)
        parcel.writeString(imagen)
        parcel.writeInt(unidades)
        parcel.writeInt(precio)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Articulo(nombre=$nombre, peso=$peso, tipo=$tipo, imagen=$imagen, unidades=$unidades, precio=$precio, id=$id)"
    }

    companion object CREATOR : Parcelable.Creator<Articulo> {
        override fun createFromParcel(parcel: Parcel): Articulo {
            return Articulo(parcel)
        }

        override fun newArray(size: Int): Array<Articulo?> {
            return arrayOfNulls(size)
        }
    }
}
