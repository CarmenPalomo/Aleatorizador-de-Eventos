package com.example.personajecreacion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import java.util.logging.Logger

class MercaderActivity : AppCompatActivity() {
    private val log: Logger = Logger.getLogger("MercaderActivity")
    private lateinit var articuloCompra: Articulo
    private var comprarActivado = false
    private var venderActivado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)
        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        val botonContinuar: Button = findViewById(R.id.continuarM)
        val botonComerciar: Button = findViewById(R.id.comerciar)
        val precioCompra: TextView = findViewById(R.id.precioObjetos)
        val objetosMercader = MercaderDataBase(applicationContext)

        val imagenMercader: ImageView = findViewById(R.id.mercaderF)

        val botonComprar: Button = findViewById(R.id.comprar)
        val botonVender: Button = findViewById(R.id.vender)
        val botonCancelar: Button = findViewById(R.id.cancelar)

        val entradaUnidades: EditText = findViewById(R.id.unidadesArticulo)
        val sinArticulosVenta: TextView = findViewById(R.id.sinArticulosVenta)
        val mensajeVender: TextView = findViewById(R.id.mensajeVender)
        var mensajeComprar: TextView = findViewById(R.id.mensajeComprar)

        botonContinuar.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }

        botonComerciar.setOnClickListener {
            botonComprar.visibility = View.VISIBLE
            botonVender.visibility = View.VISIBLE
            botonCancelar.visibility = View.VISIBLE

            botonComerciar.visibility = View.INVISIBLE
            botonContinuar.visibility = View.INVISIBLE
        }

        botonCancelar.setOnClickListener {
            val idImagenArticulo =
                resources.getIdentifier("mercader", "drawable", packageName)
            imagenMercader.setImageResource(idImagenArticulo)

            botonComerciar.visibility = View.VISIBLE
            botonContinuar.visibility = View.VISIBLE

            mensajeComprar.visibility = View.INVISIBLE
            botonComprar.visibility = View.INVISIBLE
            botonVender.visibility = View.INVISIBLE
            botonCancelar.visibility = View.INVISIBLE
            entradaUnidades.visibility = View.INVISIBLE
            sinArticulosVenta.visibility = View.INVISIBLE
            precioCompra.visibility = View.INVISIBLE
            mensajeVender.visibility = View.INVISIBLE
            comprarActivado = false
            venderActivado = false
        }

        botonComprar.setOnClickListener {
            if (!comprarActivado) {
                //si es la primera vez que le das muestras las opciones y cambias la imagen
                articuloCompra = objetosMercader.getArticuloAleatorio()
                val idImagenArticulo =
                    resources.getIdentifier(articuloCompra.getImagen(), "drawable", packageName)
                log.info("articulo para comprar $articuloCompra")
                imagenMercader.setImageResource(idImagenArticulo)
                entradaUnidades.visibility = View.VISIBLE
                precioCompra.visibility = View.VISIBLE
                botonVender.visibility = View.INVISIBLE
                precioCompra.text =
                    "Precio: ${articuloCompra.getPrecio()} unidades ${articuloCompra.getUnidades()}"
                comprarActivado = true
            } else {
                //si es la segunda vez que le das, compras
                // Si el personaje tiene oro
                if (personaje!!.getMochila()!!.tieneOro()) {
                    var unidades = 0
                    if (entradaUnidades.text.isDigitsOnly()) {
                        unidades = entradaUnidades.text.toString().toInt()
                    }
                    val precioArticulo = articuloCompra.precioPorUnidad() * unidades
                    val dineroPersonaje = personaje.getMochila()!!.devolverDinero()
                    // si el dinero que tiene el peronaje es mayor al precio del articulo
                    if (dineroPersonaje >= precioArticulo) {
                        log.info("articulo que se quiere comprar $articuloCompra")
                        log.info("precio del articulo $precioArticulo, dinero personaje $dineroPersonaje")
                        //guardamos el articulo
                        val copiaArticulo = Articulo(
                            articuloCompra.getNombre(),
                            articuloCompra.getPeso(), articuloCompra.getTipo(),
                            articuloCompra.getImagen(), unidades, precioArticulo
                        )

                        if (personaje.getMochila()!!.guardarArticulo(copiaArticulo)) {
                            // restamos el dinero al personaje
                            personaje.getMochila()!!.restarDinero(precioArticulo)
                            articuloCompra.reduceUnidades(unidades)
                            // actualizacion base de datos
                            objetosMercader.actualizarUnidades(articuloCompra)

                            entradaUnidades.visibility = View.INVISIBLE
                            botonComprar.visibility = View.INVISIBLE
                            precioCompra.visibility = View.INVISIBLE
                            mensajeComprar.text = "Objeto comprado"
                            mensajeComprar.visibility = View.VISIBLE
                        } else {
                            entradaUnidades.visibility = View.INVISIBLE
                            botonComprar.visibility = View.INVISIBLE
                            precioCompra.visibility = View.INVISIBLE
                            mensajeComprar.text = "No tienes espacio suficiente en la mochila"
                            mensajeComprar.visibility = View.VISIBLE
                        }

                    } else {
                        entradaUnidades.visibility = View.INVISIBLE
                        botonComprar.visibility = View.INVISIBLE
                        precioCompra.visibility = View.INVISIBLE
                        mensajeComprar.text = "No tienes dinero para poder comprar"
                        mensajeComprar.visibility = View.VISIBLE
                    }

                } else {
                    entradaUnidades.visibility = View.INVISIBLE
                    botonComprar.visibility = View.INVISIBLE
                    precioCompra.visibility = View.INVISIBLE
                    mensajeComprar.text = "No tienes dinero para poder comprar"
                    mensajeComprar.visibility = View.VISIBLE
                }


            }

        }

        botonVender.setOnClickListener {
            if (!venderActivado) {
                //si es la primera vez que le das, muestras todo
                val idImagenArticulo =
                    resources.getIdentifier("mochila", "drawable", packageName)
                imagenMercader.setImageResource(idImagenArticulo)
                if (personaje!!.getMochila()!!.numeroObjectos() > 0) {
                    entradaUnidades.visibility = View.VISIBLE
                } else {
                    sinArticulosVenta.visibility = View.VISIBLE
                    botonVender.visibility = View.INVISIBLE
                }
                botonCancelar.visibility = View.VISIBLE
                botonComprar.visibility = View.INVISIBLE
                botonComerciar.visibility = View.INVISIBLE
                botonContinuar.visibility = View.INVISIBLE
                venderActivado = true
            } else {
                //si es la segunda vez que le das, vendes
                var unidades = 0
                if (entradaUnidades.text.isDigitsOnly()) {
                    unidades = entradaUnidades.text.toString().toInt()
                }
                val elementosEliminados = personaje!!.getMochila()!!.eliminarArticulos(unidades)
                var precioElementos = 0
                for (elementosEliminado in elementosEliminados) {
                    precioElementos += elementosEliminado.getPrecio()
                }
                personaje.getMochila()!!.guardarDinero(precioElementos)
                objetosMercader.sumarUnidades(elementosEliminados)
                mensajeVender.visibility = View.VISIBLE
                botonVender.visibility = View.INVISIBLE
                entradaUnidades.visibility = View.INVISIBLE
            }
        }

    }
}