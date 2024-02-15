package com.example.personajecreacion

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.ImageView
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

        val PersonajeDataBase = PersonajeDataBase(applicationContext)
        val botonSiguiente: Button = findViewById(R.id.boton_siguiente2)
        val botonAtras: Button = findViewById(R.id.boton_atras1)
        val nombre_personaje: TextView = findViewById(R.id.nombre_personaje)
        val raza_personaje: TextView = findViewById(R.id.raza_personaje)
        val clase_personaje: TextView = findViewById(R.id.clase_personaje)
        val edad_personaje: TextView = findViewById(R.id.edad_personaje)

        val imagenPesonaje : ImageView = findViewById(R.id.imagenPersonaje)
        textToSpeechBtn = findViewById(R.id.textToSpeechBnt)
        val raza = intent.getStringExtra("raza")
        val clase = intent.getStringExtra("clase")
        val edad = intent.getStringExtra("edad")
        val nombre = intent.getStringExtra("nombre")
        val imagen = intent.getStringExtra("imagen")
        val idPersonaje = intent.getStringExtra("userId")
        var ruta = resources.getIdentifier(imagen,"drawable",packageName)
        imagenPesonaje.setImageResource(ruta)
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


        var textazo = nombre_personaje.text.toString() +" "+ raza_personaje.text.toString() +" "+ clase_personaje.text.toString() +" "+ edad_personaje.text.toString()
        textToSpeechBtn.setOnClickListener {
            if (nombre_personaje.text.toString().isNotEmpty()) {
                textToSpeech.speak(
                    textazo.trim(),
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null
                )
            } else {

            }
        }

        botonAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        botonSiguiente.setOnClickListener {
            var personaje = Personaje(
                idPersonaje,
                nombre, edad, raza, clase,
                Mochila(9, ArrayList())
            )

            if (PersonajeDataBase.personajeRegistrado(personaje)) {
                personaje = PersonajeDataBase.getPersonaje(personaje.getIdPersonaje())!!
            } else {
                PersonajeDataBase.insertarPersonaje(personaje)
            }

            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }
    }
}