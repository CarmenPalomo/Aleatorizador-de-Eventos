package com.example.personajecreacion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MostrarDatos: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_datos)

        var personaje : Personaje
        val botonSiguiente: Button = findViewById(R.id.boton_siguiente2)
        val botonAtras: Button = findViewById(R.id.boton_atras1)
        val nombre_personaje : TextView = findViewById<TextView>(R.id.nombre_personaje)
        val raza_personaje : TextView =findViewById(R.id.raza_personaje)
        val clase_personaje : TextView = findViewById(R.id.clase_personaje)
        val edad_personaje : TextView = findViewById(R.id.edad_personaje)

        var raza = intent.getStringExtra("raza")
        var clase = intent.getStringExtra("clase")
        var edad = intent.getStringExtra("edad")
        var nombre = intent.getStringExtra("nombre")

        nombre_personaje.text = "El nombre del personaje es: $nombre"
        raza_personaje.text = "La raza del personaje es: $raza"
        clase_personaje.text = "La clase del personaje es: $clase"
        edad_personaje.text = "La edad del personaje es: $edad"

        personaje = Personaje("nombre","viejo","raza","clase")


        botonAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        botonSiguiente.setOnClickListener {
            val intent = Intent(this, Aventura::class.java)
            intent.putExtra("Personaje",personaje)
            startActivity(intent)
        }

    }
}
