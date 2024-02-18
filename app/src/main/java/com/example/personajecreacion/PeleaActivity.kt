package com.example.personajecreacion

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger


class PeleaActivity : AppCompatActivity() {

    private lateinit var personajeDataBase: PersonajeDataBase
    private lateinit var objDataBase: ObjectosDataBase
    private lateinit var vidaMonstruo: ProgressBar
    private lateinit var vidaJugador: ProgressBar
    private lateinit var atacarButton: Button
    private var entra : Boolean = false
    private lateinit var personaje: Personaje
    private lateinit var monstruo: Monstruo
    private lateinit var imageView : ImageView
    private lateinit var continuar : Button
    private lateinit var mercader : Button
    private lateinit var mediaplayer : MediaPlayer
    val log = Logger.getLogger("PeleaActivity")
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelea)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        objDataBase = ObjectosDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        monstruo = intent.getParcelableExtra("Monstruo") !!

        vidaMonstruo = findViewById(R.id.vidaMonstruo)
        vidaJugador = findViewById(R.id.vidaJugador)
        atacarButton = findViewById(R.id.atacarButton)
        imageView = findViewById(R.id.imagenPersonaje)
        continuar = findViewById(R.id.continuar)
        mercader = findViewById(R.id.mercaderP)
        personajeDataBase = PersonajeDataBase(applicationContext)
        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);

        vidaJugador.max = personaje.getSalud()!!

        val musicaMin = intent.getIntExtra("musicaMin",0)
        val estadoM = intent.getBooleanExtra("estadoM",false)
        mediaplayer.seekTo(musicaMin)
        if (estadoM){
            mediaplayer.start()
        }



        atacarButton.setOnClickListener {
            Ataques()
        }

        mercader.setOnClickListener {
            val intent = Intent(this, MercaderActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            mediaplayer.pause()
            startActivity(intent)
        }

        continuar.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            mediaplayer.pause()
            startActivity(intent)
        }

        if (personaje.getClase() != null && personaje.getRaza() != null && personaje.getEstadoVital() != null) {
            when(personaje.getRaza()) {
                "Humano"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.humano_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.humano_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.humano_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.humano_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.humano_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.humano_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.humano_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.humano_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.humano_guerrero_joven)
                                }
                            }
                        }
                    }
                }
                "Elfo"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.elfo_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.elfo_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.elfo_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.elfo_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.elfo_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.elfo_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.elfo_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.elfo_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.elfo_guerrero_joven)
                                }
                            }
                        }
                    }
                }
                "Enano"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.enano_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.enano_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.enano_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.enano_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.enano_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.enano_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.enano_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.enano_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.enano_guerrero_joven)
                                }
                            }
                        }
                    }
                }

                "Maldito"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.maldito_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.maldito_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.maldito_brujo_adolescente)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.maldito_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.maldito_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.maldito_mago_adolescente)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.maldito_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.maldito_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.maldito_guerrero_adolescente)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    private fun Ataques() {

        if (monstruo.getSalud() > 0 && personaje .getSalud()!! > 0) {
            dañoAlMonstruo()

            if (monstruo.getSalud() > 0) {
                dañoAlPersonaje()
            }
        }

        resultadoPelea()
    }

    private fun dañoAlMonstruo() {
        var currentHealth: Int = vidaMonstruo.progress
        currentHealth = monstruo.getSalud() - personaje.getAtaque()!!
        monstruo.setSalud(currentHealth)
        val newHealth = monstruo.getSalud()
        if (newHealth != null) {
            vidaMonstruo.progress = if (newHealth < 0) 0 else newHealth
        }
    }

    private fun dañoAlPersonaje() {
        var currentHealth = vidaJugador.progress
        currentHealth = personaje.getSalud()!! - monstruo.getAtaque()
        personaje.setSalud(currentHealth)
        val newHealth = personaje.getSalud()
        if (newHealth != null) {
            vidaJugador.progress = if (newHealth < 0) 0 else newHealth
        }
    }

    private fun resultadoPelea() {
        if (monstruo.getSalud() <= 0) {
            Toast.makeText(this, "Has derrotado al monstruo", Toast.LENGTH_SHORT).show()
            atacarButton.visibility = View.INVISIBLE
            continuar.visibility= View.VISIBLE
            var articulo : Articulo = objDataBase.getArticuloAleatorioMonstruo()
            entra = personaje.getMochila()!!.guardarArticulo(articulo)
            if (!entra){
                mercader.visibility = View.VISIBLE
            }
            Toast.makeText(this, articulo.toString(), Toast.LENGTH_SHORT).show()

        } else if (personaje.getSalud()!! <= 0) {
            Toast.makeText(this, "El monstruo te ha derrotado pero te resucitaré", Toast.LENGTH_SHORT).show()
            atacarButton.visibility = View.INVISIBLE
            continuar.visibility= View.VISIBLE
            personaje = Personaje(personaje.getIdPersonaje(),personaje.getNombre(),personaje.getEstadoVital(),personaje.getRaza(),personaje.getClase(),personaje.getMochila())

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