package com.example.personajecreacion

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MostrarDatosActivity : AppCompatActivity() {
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var textToSpeechBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_datos)
        var personaje: Personaje
        val botonSiguiente: Button = findViewById(R.id.boton_siguiente2)
        val botonAtras: Button = findViewById(R.id.boton_atras1)
        val nombre_personaje: TextView = findViewById<TextView>(R.id.nombre_personaje)
        val raza_personaje: TextView = findViewById(R.id.raza_personaje)
        val clase_personaje: TextView = findViewById(R.id.clase_personaje)
        val edad_personaje: TextView = findViewById(R.id.edad_personaje)
        textToSpeechBtn = findViewById(R.id.textToSpeechBnt)
        var raza = intent.getStringExtra("raza")
        var clase = intent.getStringExtra("clase")
        var edad = intent.getStringExtra("edad")
        var nombre = intent.getStringExtra("nombre")
        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val resultado = textToSpeech.setLanguage(Locale.getDefault())
                if (resultado == TextToSpeech.LANG_MISSING_DATA || resultado == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "lenguaje no soportado", Toast.LENGTH_LONG).show()
                }
            }
        }
        nombre_personaje.text = "El nombre del personaje es: $nombre"
        raza_personaje.text = "La raza del personaje es: $raza"
        clase_personaje.text = "La clase del personaje es: $clase"
        edad_personaje.text = "La edad del personaje es: $edad"
        textToSpeechBtn.setOnClickListener {
            if (nombre_personaje.text.toString().isNotEmpty()) {
                textToSpeech.speak(
                    nombre_personaje.text.toString().trim(),
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
            } else {
            }
            if (raza_personaje.text.toString().isNotEmpty()) {
                textToSpeech.speak(
                    raza_personaje.text.toString().trim(),
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
            } else {
            }
            if (clase_personaje.text.toString().isNotEmpty()) {
                textToSpeech.speak(
                    clase_personaje.text.toString().trim(),
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
            } else {
            }
            if (edad_personaje.text.toString().isNotEmpty()) {
                textToSpeech.speak(
                    edad_personaje.text.toString().trim(),
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
            } else {
            }
        }

        personaje = Personaje(
            nombre, edad, raza, clase,
            Mochila(9, ArrayList())
        )

        botonAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        botonSiguiente.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }
    }
}