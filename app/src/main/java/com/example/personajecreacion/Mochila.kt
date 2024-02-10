package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable

class Mochila(var espacio: Int, private val articulos: ArrayList<Articulo>) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        (parcel.createTypedArrayList(Articulo.CREATOR)
            ?: emptyList<Articulo>()) as ArrayList<Articulo>
    ) {
    }

    fun numeroObjectos(): Int {
        var contador = 0
        for (articulo in articulos) {
            if (articulo.getTipo() != Articulo.TipoArt.ORO) {
                contador++
            }
        }
        return contador
    }

    fun guardarArticulo(nuevoArticulo: Articulo) : Boolean {
        if (espacio <= 0 || nuevoArticulo.getUnidades() > espacio) {
            return false
        }

        if (Articulo.TipoArt.ORO == nuevoArticulo.getTipo()) {
            var indice = 0
            var articulo: Articulo? = null
            while (indice < articulos.size && articulo == null) {
                if (articulos[indice].getTipo() == Articulo.TipoArt.ORO) {
                    articulo = articulos[indice]
                }
                indice++
            }

            if (articulo == null) {
                articulos.add(nuevoArticulo)
                espacio--
            } else {
                articulo.sumaPrecio(nuevoArticulo.getPrecio())
            }
        } else {
            articulos.add(nuevoArticulo)
            espacio--
        }

        return true
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(espacio)
        parcel.writeTypedList(articulos)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Mochila(espacio=$espacio, articulos=$articulos)"
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