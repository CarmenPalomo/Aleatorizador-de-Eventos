package com.example.personajecreacion

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import java.util.logging.Logger

class MostrarDatosActivity : AppCompatActivity() {
    private val log: Logger = Logger.getLogger("MostrarDatosActivity")
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var textToSpeechBtn: Button
    private lateinit var mediaplayer : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrar_datos)

        val botonSiguiente: Button = findViewById(R.id.boton_siguiente2)
        val botonAtras: Button = findViewById(R.id.boton_atras1)
        val nombre_personaje: TextView = findViewById(R.id.nombre_personaje)
        val raza_personaje: TextView = findViewById(R.id.raza_personaje)
        val clase_personaje: TextView = findViewById(R.id.clase_personaje)
        val edad_personaje: TextView = findViewById(R.id.edad_personaje)
        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);

        val imagenPesonaje: ImageView = findViewById(R.id.imagenPersonaje)
        textToSpeechBtn = findViewById(R.id.textToSpeechBnt)
        val raza = intent.getStringExtra("raza")
        val clase = intent.getStringExtra("clase")
        val edad = intent.getStringExtra("edad")
        val nombre = intent.getStringExtra("nombre")
        val imagen = intent.getIntExtra("imagen", 0)
        val idPersonaje = intent.getStringExtra("userId")
        log.info("El personaje tiene $raza, $clase, $edad, $nombre, $imagen, $idPersonaje")

        val musicaMin = intent.getIntExtra("musicaMin",0)
        val estadoM = intent.getBooleanExtra("estadoM",false)
        mediaplayer.seekTo(musicaMin)
        if (estadoM){
            mediaplayer.start()
        }
        imagenPesonaje.setImageResource(imagen)
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


        var textazo =
            nombre_personaje.text.toString() + " " + raza_personaje.text.toString() + " " + clase_personaje.text.toString() + " " + edad_personaje.text.toString()
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
        val personaje = Personaje(
            idPersonaje,
            nombre, edad, raza, clase,
            Mochila(9, ArrayList())
        )

        botonAtras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userId", idPersonaje)
            startActivity(intent)
        }
        botonSiguiente.setOnClickListener {

            val personajeDataBase = PersonajeDataBase(applicationContext)
            personajeDataBase.insertarPersonaje(personaje)
            personajeDataBase.getPersonaje(personaje.getIdPersonaje())

            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            log.info("Se ha pasado el personaje ${personaje.getIdPersonaje()}, ${personaje.getNombre()}")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.suenaM -> {
                mediaplayer.start()
                log.info("Musica sonando valor ${mediaplayer.isPlaying}")
                true
            }
            R.id.paraM ->{
                mediaplayer.pause()
                log.info("Musica parada valor ${mediaplayer.isPlaying}")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}