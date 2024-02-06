package com.example.personajecreacion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Mercader : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)
        val comerciar: Button = findViewById(R.id.comerciar)
        val continuar: Button = findViewById(R.id.continuarM)
        var imagen: ImageView = findViewById(R.id.mercaderF)
        val comprar: Button = findViewById(R.id.comprar)
        val vender: Button = findViewById(R.id.vender)
        val cancelar: Button = findViewById(R.id.cancelar)
        val mochila: Mochila? = null
        var art1: TextView = findViewById(R.id.art1)
        var art2: TextView = findViewById(R.id.art2)
        var art3: TextView = findViewById(R.id.art3)
        var art4: TextView = findViewById(R.id.art4)
        var art5: TextView = findViewById(R.id.art5)
        var art6: TextView = findViewById(R.id.art6)
        var art7: TextView = findViewById(R.id.art7)
        var art8: TextView = findViewById(R.id.art8)
        var art9: TextView = findViewById(R.id.art9)
        var art10: TextView = findViewById(R.id.art10)
        var cant1: EditText = findViewById(R.id.cant1)
        var cant2: EditText = findViewById(R.id.cant2)
        var cant3: EditText = findViewById(R.id.cant3)
        var cant4: EditText = findViewById(R.id.cant4)
        var cant5: EditText = findViewById(R.id.cant5)
        var cant6: EditText = findViewById(R.id.cant6)
        var cant7: EditText = findViewById(R.id.cant7)
        var cant8: EditText = findViewById(R.id.cant8)
        var cant9: EditText = findViewById(R.id.cant9)
        var cant10: EditText = findViewById(R.id.cant10)
        val dbHelper2 = MercaderDataBase(this)

        comerciar.setOnClickListener {
            comerciar.setVisibility(View.INVISIBLE)
            continuar.setVisibility(View.INVISIBLE)
            comprar.setVisibility(View.VISIBLE)
            vender.setVisibility(View.VISIBLE)
            cancelar.setVisibility(View.VISIBLE)
            comprar.setOnClickListener {
                val articulos = dbHelper2.getArticulos()
                var num = 1
                articulos.forEach {
                    when (num) {
                        1 -> {
                            art1.setVisibility(View.VISIBLE)
                            cant1.setVisibility(View.VISIBLE)
                            art1.text = it.getNombre()
                        }

                        2 -> {
                            art2.setVisibility(View.VISIBLE)
                            cant2.setVisibility(View.VISIBLE)
                            art2.text = it.getNombre()
                        }

                        3 -> {
                            art3.setVisibility(View.VISIBLE)
                            cant3.setVisibility(View.VISIBLE)
                            art3.text = it.getNombre()
                        }

                        4 -> {
                            art4.setVisibility(View.VISIBLE)
                            cant4.setVisibility(View.VISIBLE)
                            art4.text = it.getNombre()
                        }

                        5 -> {
                            art5.setVisibility(View.VISIBLE)
                            cant5.setVisibility(View.VISIBLE)
                            art5.text = it.getNombre()
                        }

                        6 -> {
                            art6.setVisibility(View.VISIBLE)
                            cant6.setVisibility(View.VISIBLE)
                            art6.text = it.getNombre()
                        }

                        7 -> {
                            art7.setVisibility(View.VISIBLE)
                            cant7.setVisibility(View.VISIBLE)
                            art7.text = it.getNombre()
                        }

                        8 -> {
                            art8.setVisibility(View.VISIBLE)
                            cant8.setVisibility(View.VISIBLE)
                            art8.text = it.getNombre()
                        }

                        9 -> {
                            art9.setVisibility(View.VISIBLE)
                            cant9.setVisibility(View.VISIBLE)
                            art9.text = it.getNombre()
                        }

                        10 -> {
                            art10.setVisibility(View.VISIBLE)
                            cant10.setVisibility(View.VISIBLE)
                            art10.text = it.getNombre()
                        }
                    }
                    num++
                }
                vender.setOnClickListener {
                    imagen.setImageResource(R.drawable.mochila)
                }
                cancelar.setOnClickListener {
                    comerciar.setVisibility(View.VISIBLE)
                    continuar.setVisibility(View.VISIBLE)
                    comprar.setVisibility(View.INVISIBLE)
                    vender.setVisibility(View.INVISIBLE)
                    cancelar.setVisibility(View.INVISIBLE)
                    imagen.setImageResource(R.drawable.mercader)
                }
            }
            continuar.setOnClickListener {
                val intent = Intent(this, Aventura::class.java)
                startActivity(intent)
            }
        }
    }
}

/*
num = 1
articulos.forEach {
when (num) {
1 -> {
if (cant1.text.toString().toInt() > 0) {
val articulo: Articulo = (it.copy(
nombre = it.getNombre(),
tipo = it.getTipo(),
peso = it.getPeso(),
unidades = cant1.text.toString().toInt(),
precio = 0
))
mochila?.guardarArticulo(articulo)
}
}
2 -> {
if (cant2.text.toString().toInt() > 0) {
val articulo: Articulo = (it.copy(
nombre = it.getNombre(),
tipo = it.getTipo(),
peso = it.getPeso(),
unidades = cant2.text.toString().toInt(),
precio = 0
))
mochila?.guardarArticulo(articulo)
}
}
7 -> {
if (cant7.text.toString().toInt() > 0) {
val articulo: Articulo = (it.copy(
nombre = it.getNombre(),
tipo = it.getTipo(),
peso = it.getPeso(),
unidades = cant7.text.toString().toInt(),
precio = 0
))
mochila?.guardarArticulo(articulo)
}
}
8 -> {
if (cant8.text.toString().toInt() > 0) {
val articulo: Articulo = (it.copy(
nombre = it.getNombre(),
tipo = it.getTipo(),
peso = it.getPeso(),
unidades = cant8.text.toString().toInt(),
precio = 0
))
mochila?.guardarArticulo(articulo)
}
}
9 -> {
if (cant9.text.toString().toInt() > 0) {
val articulo: Articulo = (it.copy(
nombre = it.getNombre(),
tipo = it.getTipo(),
peso = it.getPeso(),
unidades = cant9.text.toString().toInt(),
precio = 0
))
mochila?.guardarArticulo(articulo)
}
}
10 -> {
if (cant10.text.toString().toInt() > 0) {
val articulo: Articulo = (it.copy(
nombre = it.getNombre(),
tipo = it.getTipo(),
peso = it.getPeso(),
unidades = cant10.text.toString().toInt(),
precio = 0
))
mochila?.guardarArticulo(articulo)
}
}
}
num++
}
*/