package com.example.personajecreacion

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import java.util.logging.Logger

class GuardiaActivity : AppCompatActivity() {
    private lateinit var boton: Button
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase
    private lateinit var mediaplayer : MediaPlayer
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var texto : TextView
    private lateinit var textazo : String
    val log = Logger.getLogger("GuardiaActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardia)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        boton = findViewById(R.id.botonEchado)
        texto = findViewById(R.id.textoPrincipio)

        textazo = texto.text.toString()

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val resultado = textToSpeech.setLanguage(Locale.getDefault())
                if (resultado == TextToSpeech.LANG_MISSING_DATA || resultado == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "lenguaje no soportado", Toast.LENGTH_LONG).show()
                }
            }
        }

        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);


        val musicaMin = intent.getIntExtra("musicaMin",0)
        val estadoM = intent.getBooleanExtra("estadoM",false)
        mediaplayer.seekTo(musicaMin)
        if (estadoM){
            mediaplayer.start()
        }



        boton.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            mediaplayer.pause()
            startActivity(intent)
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