package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable

class Mochila(

    private var espacio: Int,
    private val articulos: ArrayList<Articulo>
) : Parcelable {
    private var idMochila: Long? = null

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        (parcel.createTypedArrayList(Articulo.CREATOR)
            ?: emptyList<Articulo>()) as ArrayList<Articulo>
    ) {
        idMochila = parcel.readLong()
    }

    fun getEspacio(): Int {
        return espacio
    }

    fun setIdMochila(id: Long) {
        idMochila = id
    }

    fun restarDinero(dinero: Int) {
        var indice = 0
        var articuloOro: Articulo? = null
        while (indice < articulos.size && articuloOro == null) {
            if (articulos[indice].getTipo() == Articulo.TipoArt.ORO) {
                articuloOro = articulos[indice]
            }
            indice++
        }
        articuloOro?.restaPrecio(dinero)
    }

    fun devolverDinero(): Int {
        for (articulo in articulos) {
            if (articulo.getTipo() == Articulo.TipoArt.ORO) {
                return articulo.getPrecio()
            }
        }

        return 0
    }

    fun tieneOro(): Boolean {
        for (articulo in articulos) {
            if (articulo.getTipo() == Articulo.TipoArt.ORO) {
                return true
            }
        }
        return false
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

    fun guardarArticulo(nuevoArticulo: Articulo): Boolean {
        if (espacio <= 0 || nuevoArticulo.getUnidades() > espacio) {
            return false
        }

        articulos.add(nuevoArticulo)
        espacio--
        return true
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(espacio)
        parcel.writeTypedList(articulos)
        parcel.writeLong(idMochila!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Mochila(espacio=$espacio, articulos=$articulos)"
    }

    fun eliminarArticulos(unidades: Int): ArrayList<Articulo> {
        var articulosEliminados: ArrayList<Articulo> = arrayListOf()
        var unidadesAReducir = unidades
        var indice = 0
        var unidadesArticulo = 0
        while (indice < articulos.size && unidadesAReducir > 0) {
            if (Articulo.TipoArt.ORO != articulos[indice].getTipo()) {
                if (articulos[indice].getUnidades() <= unidadesAReducir) {
                    unidadesArticulo = articulos[indice].getUnidades()
                    unidadesAReducir -= unidadesArticulo
                    articulosEliminados.add(
                        Articulo(
                            articulos[indice].getNombre(),
                            articulos[indice].getPeso(), articulos[indice].getTipo(),
                            articulos[indice].getImagen(), articulos[indice].getUnidades(),
                            articulos[indice].getPrecio()
                        )
                    )
                    articulos[indice].reduceUnidades(unidadesArticulo)
                } else {
                    val precioAntes = articulos[indice].getPrecio()
                    articulos[indice].reduceUnidades(unidadesAReducir)
                    unidadesAReducir = 0
                    articulosEliminados.add(
                        Articulo(
                            articulos[indice].getNombre(),
                            articulos[indice].getPeso(), articulos[indice].getTipo(),
                            articulos[indice].getImagen(), unidadesAReducir,
                            precioAntes - articulos[indice].getPrecio()
                        )
                    )
                }
            }
            indice++
        }

        indice = 0
        while (indice < articulos.size) {
            if (articulos[indice].getUnidades() == 0) {
                articulos.remove(articulos[indice])
                espacio++
            } else {
                indice++
            }
        }
        return articulosEliminados;
    }

    fun guardarDinero(dineroObtenido: Int) {
        var indice = 0
        var articuloOro: Articulo? = null
        while (indice < articulos.size && articuloOro == null) {
            if (articulos[indice].getTipo() == Articulo.TipoArt.ORO) {
                articuloOro = articulos[indice]
            }
            indice++
        }

        if (articuloOro != null) {
            articuloOro.sumaPrecio(dineroObtenido)
        } else {
            articulos.add(Articulo("ORO", 0, Articulo.TipoArt.ORO, "oro", 1, dineroObtenido))
            espacio--
        }
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