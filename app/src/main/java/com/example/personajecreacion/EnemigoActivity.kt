package com.example.personajecreacion

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import java.util.logging.Logger

class EnemigoActivity : AppCompatActivity() {
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase
    private lateinit var monstruoDataBase: MonstruoDataBase
    private lateinit var mediaplayer : MediaPlayer
    val log = Logger.getLogger("EnemigoActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        monstruoDataBase = MonstruoDataBase(applicationContext)
        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        val monstruo : Monstruo = Monstruo("Belzebu", 1)
        val luchar: Button = findViewById(R.id.luchar)
        val huir : Button = findViewById(R.id.huir)

        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);


        val musicaMin = intent.getIntExtra("musicaMin",0)
        val estadoM = intent.getBooleanExtra("estadoM",false)
        mediaplayer.seekTo(musicaMin)
        if (estadoM){
            mediaplayer.start()
        }

//hacer
        luchar.setOnClickListener {
           var monstruo =  monstruoDataBase.getMonstruoAleatorio()
            val intent = Intent(this,PeleaActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("Monstruo", monstruo)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            intent.putExtra("monstruo", monstruo
            mediaplayer.pause()
            startActivity(intent)
        }

        huir.setOnClickListener {
            val intent = Intent(this,AventuraActivity::class.java)
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}