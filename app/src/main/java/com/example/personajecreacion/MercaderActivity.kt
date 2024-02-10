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

class MercaderActivity : AppCompatActivity() {
    private var comprarActivado = false
    private var venderActivado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)
        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        val botonContinuar: Button = findViewById(R.id.continuarM)
        val botonComerciar: Button = findViewById(R.id.comerciar)
        val precio: TextView = findViewById(R.id.precioObjetos)

        val imagenMercader: ImageView = findViewById(R.id.mercaderF)

        val botonComprar: Button = findViewById(R.id.comprar)
        val botonVender: Button = findViewById(R.id.vender)
        val botonCancelar: Button = findViewById(R.id.cancelar)

        val entradaUnidades: EditText = findViewById(R.id.unidadesArticulo)
        val sinArticulosVenta: TextView = findViewById(R.id.sinArticulosVenta)

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

            botonComprar.visibility = View.INVISIBLE
            botonVender.visibility = View.INVISIBLE
            botonCancelar.visibility = View.INVISIBLE
            entradaUnidades.visibility = View.INVISIBLE
            sinArticulosVenta.visibility = View.INVISIBLE
            precio.visibility = View.INVISIBLE
            comprarActivado = false
            venderActivado = false
        }

        botonComprar.setOnClickListener {
            if (!comprarActivado) {
                //si es la primera vez que le das muestras todo
                val objetosMercader = MercaderDataBase(applicationContext)
                val articulo = objetosMercader.getArticuloAleatorio()
                val idImagenArticulo =
                    resources.getIdentifier(articulo.getImagen(), "drawable", packageName)

                imagenMercader.setImageResource(idImagenArticulo)

                entradaUnidades.visibility = View.VISIBLE
                precio.visibility = View.VISIBLE
                botonCancelar.visibility = View.VISIBLE

                botonVender.visibility = View.INVISIBLE
                botonComerciar.visibility = View.INVISIBLE
                botonContinuar.visibility = View.INVISIBLE

                precio.text = "Precio: ${articulo.getPrecio()}"
                comprarActivado = true
            } else {
                //si es la segudna vez que le das, compras
                //TODO: comprar
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
                //si es la segunda vez que le das, compras
                //TODO: vender
            }
        }

    }
}