package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable

class Mochila(espacio: Int, articulos: ArrayList<Articulo>) : Parcelable {
    val espacio = espacio
    val articulos: ArrayList<Articulo> = ArrayList()

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        (parcel.createTypedArrayList(Articulo.CREATOR)
            ?: emptyList<Articulo>()) as ArrayList<Articulo>
    ) {
    }

    fun getArticulos(): ArrayList<Articulo> {
        return this.articulos
    }

    fun guardarArticulo(nuevoArticulo: Articulo) {
        if (articulos.size < this.espacio) {
            articulos.add(nuevoArticulo)
        }
    }

    fun getEspacio(): Int {
        return espacio
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(espacio)
        parcel.writeTypedList(articulos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mochila> {
        override fun createFromParcel(parcel: Parcel): Mochila {
            return Mochila(parcel)
        }

        override fun newArray(size: Int): Array<Mochila?> {
            return arrayOfNulls(size)
        }
    }
}