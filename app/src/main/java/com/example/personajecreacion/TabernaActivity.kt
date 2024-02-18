package com.example.personajecreacion

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import java.util.Locale
import java.util.logging.Logger

class TabernaActivity : AppCompatActivity() {
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase
    private lateinit var mediaplayer : MediaPlayer
    val log = Logger.getLogger("TabernaActivity")
    private lateinit var textazo : String
    private lateinit var textazo2 : String
    private lateinit var textazo3 : String
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var textoPagar : TextView
    private lateinit var textoNoPagar : TextView
    private lateinit var textoPrecio : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taberna)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personaje = intent.getParcelableExtra("Personaje")!!

        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        val entrar : Button = findViewById(R.id.entrar)
        val marcharse : Button = findViewById(R.id.irse)
        val pagar : Button = findViewById(R.id.pagar)
        textoPagar = findViewById(R.id.textoPagar)
        textoPrecio = findViewById(R.id.textoPrecio)
        textoNoPagar = findViewById(R.id.textoNoPagar)
        val imagenTabernaInterior : ImageView = findViewById(R.id.imagenTabernaInterior)
        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);


        textazo = textoPagar.text.toString()
        textazo2 = textoNoPagar.text.toString()
        textazo3 = textoPrecio.text.toString()

        textToSpeech = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val resultado = textToSpeech.setLanguage(Locale.getDefault())
                if (resultado == TextToSpeech.LANG_MISSING_DATA || resultado == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "lenguaje no soportado", Toast.LENGTH_LONG).show()
                }
            }
        }

        val musicaMin = intent.getIntExtra("musicaMin",0)
        val estadoM = intent.getBooleanExtra("estadoM",false)
        mediaplayer.seekTo(musicaMin)
        if (estadoM){
            mediaplayer.start()
        }



        entrar.setOnClickListener {
            entrar.visibility = View.INVISIBLE
            pagar.visibility = View.VISIBLE
            imagenTabernaInterior.visibility = View.VISIBLE
            marcharse.visibility = View.VISIBLE
            textoPagar.visibility = View.INVISIBLE
            textoNoPagar.visibility = View.INVISIBLE
            textoPrecio.visibility = View.VISIBLE

        }

        marcharse.setOnClickListener {

            var numAtaque = (1..4).random()
            personaje!!.ReduceAtaque(numAtaque)
            var numVida = (1..4).random()
            personaje!!.ReduceSalud(numVida)

            Toast.makeText(this, "Has perdido $numAtaque de ataque y $numVida de vida. Ahora tu personaje tiene ${personaje.getAtaque()} de ataque y  ${personaje.getSalud()} de vida}}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            mediaplayer.pause()
            startActivity(intent)
        }

        pagar.setOnClickListener {

            if (personaje!!.getMochila()!!.tieneOro()){
                var num = (1..4).random()
                personaje.AumentoSalud(num)
                Toast.makeText(this, "Has aumentado la salud un $num ahora tu personaje tiene ${personaje.getSalud()} de salud", Toast.LENGTH_LONG).show()

                personaje.getMochila()!!.restarDinero(5)
                entrar.visibility = View.INVISIBLE
                pagar.visibility = View.INVISIBLE
                marcharse.visibility = View.VISIBLE
                textoPagar.visibility = View.VISIBLE
                textoNoPagar.visibility = View.INVISIBLE
                textoPrecio.visibility = View.INVISIBLE

            }else{

                entrar.visibility = View.INVISIBLE
                pagar.visibility = View.INVISIBLE
                marcharse.visibility = View.VISIBLE
                textoPagar.visibility = View.INVISIBLE
                textoNoPagar.visibility = View.VISIBLE
                textoPrecio.visibility = View.INVISIBLE

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
                if (textoPagar.isVisible){
                    textToSpeech.speak(
                        textazo.trim(),
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null)
                }
                if (textoNoPagar.isVisible){
                    textToSpeech.speak(
                        textazo2.trim(),
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null)
                }
                if (textoPrecio.isVisible){
                    textToSpeech.speak(
                        textazo3.trim(),
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}