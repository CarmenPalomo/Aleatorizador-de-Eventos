package com.example.personajecreacion

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import java.util.logging.Logger

class AventuraActivity : AppCompatActivity() {
    private val log = Logger.getLogger("AventuraActivity")
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase
    private lateinit var mediaplayer : MediaPlayer
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var texto : TextView
    private lateinit var textazo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aventura)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        log.info("personaje obtenido $personaje")
        var dado: ImageButton = findViewById(R.id.dado)
        texto = findViewById(R.id.textView4)
        textazo = texto.text.toString()
        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);


        val musicaMin = intent.getIntExtra("musicaMin",0)
        val estadoM = intent.getBooleanExtra("estadoM",false)
        mediaplayer.seekTo(musicaMin)
        if (estadoM){
            mediaplayer.start()
        }

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val resultado = textToSpeech.setLanguage(Locale.getDefault())
                if (resultado == TextToSpeech.LANG_MISSING_DATA || resultado == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "lenguaje no soportado", Toast.LENGTH_LONG).show()
                }
            }
        }

        dado.setOnClickListener {
            val num = (1..4).random()
            val intent: Intent
            when (num) {
                1 -> {
                    intent = Intent(this, ObjetoActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    intent.putExtra("musicaMin", mediaplayer.currentPosition)
                    intent.putExtra("estadoM", mediaplayer.isPlaying)
                    mediaplayer.pause()
                    startActivity(intent)
                }

                2 -> {
                    intent = Intent(this, CiudadActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    intent.putExtra("musicaMin", mediaplayer.currentPosition)
                    intent.putExtra("estadoM", mediaplayer.isPlaying)
                    mediaplayer.pause()
                    startActivity(intent)
                }

                3 -> {
                    intent = Intent(this, MercaderActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    intent.putExtra("musicaMin", mediaplayer.currentPosition)
                    intent.putExtra("estadoM", mediaplayer.isPlaying)
                    mediaplayer.pause()
                    startActivity(intent)
                }

                4 -> {
                    intent = Intent(this, EnemigoActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    intent.putExtra("musicaMin", mediaplayer.currentPosition)
                    intent.putExtra("estadoM", mediaplayer.isPlaying)
                    mediaplayer.pause()
                    startActivity(intent)
                }
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.botonGuardar -> {
                personajeDataBase.actualizarPersonaje(personaje)
                true
            }
            R.id.botonDialogFlow->{
                var intent = Intent(this,Dialogflow::class.java)
                intent.putExtra("Personaje", personaje)
                mediaplayer.pause()
                startActivity(intent)
                true
            }
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
            R.id.textToSpeechBtn -> {
                textToSpeech.speak(
                    textazo.trim(),
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    null)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}