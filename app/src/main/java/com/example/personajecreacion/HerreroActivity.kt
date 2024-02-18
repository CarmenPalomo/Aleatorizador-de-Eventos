package com.example.personajecreacion

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.util.logging.Logger

class HerreroActivity : AppCompatActivity() {
    private lateinit var botonPelea: Button
    private lateinit var botonMercader: Button
    private lateinit var botonReparar: Button
    private lateinit var botonCrear: Button
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase
    private lateinit var mediaplayer : MediaPlayer
    val log = Logger.getLogger("HerreroActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herrero)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        botonCrear = findViewById(R.id.crear)
        botonReparar = findViewById(R.id.reparar)
        botonPelea = findViewById(R.id.pelea)
        botonMercader = findViewById(R.id.mercader)
        var ImagenMartillo: ImageView = findViewById(R.id.ImagenMartillo)
        var ImagenYelmo: ImageView = findViewById(R.id.ImagenYelmo)
        var ImagenEspada: ImageView = findViewById(R.id.ImagenEspada)
        var ImagenHerrero: ImageView = findViewById(R.id.ImagenHerrero)
        var marcharse: Button = findViewById(R.id.marcharse)
        var martillo: Button = findViewById(R.id.martillo)
        var yelmo: Button = findViewById(R.id.yelmo)
        var espada_infernal: Button = findViewById(R.id.espada)

        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);


        val musicaMin = intent.getIntExtra("musicaMin",0)
        val estadoM = intent.getBooleanExtra("estadoM",false)
        mediaplayer.seekTo(musicaMin)
        if (estadoM){
            mediaplayer.start()
        }


        var lista = personaje.getMochila()?.getArticulos()

        var garra = 0
        var pelo = 0
        var bronce = 0
        var hierro = 0
        var lingote = 0

        botonPelea.visibility = View.INVISIBLE

        var numeroUnidades = 0
        for (articulo in lista!!) {
            if (articulo.getTipo() == Articulo.TipoArt.OBJETOMONSTRUO) {
                numeroUnidades += articulo.getUnidades()
            }
        }

        for (articulo in lista) {
            if (articulo.getNombre() == "GARRAS_MONSTRUO") {
                garra += 1
            } else if (articulo.getNombre() == "BRONCE") {
                bronce += 1
            } else if (articulo.getNombre() == "PELO") {
                pelo += 1
            } else if (articulo.getNombre() == "HIERRO") {
                hierro += 1
            } else if (articulo.getNombre() == "LINGOTE") {
                lingote += 1
            }
        }


        botonCrear.setOnClickListener {


            botonCrear.visibility = View.INVISIBLE
            botonReparar.visibility = View.INVISIBLE
            martillo.visibility = View.VISIBLE
            yelmo.visibility = View.VISIBLE
            espada_infernal.visibility = View.VISIBLE
        }

        martillo.setOnClickListener {
            if (garra >= 1 && hierro >= 1) {
                var martillo: Articulo? = null
                var indice = 0
                while (indice < lista.size && martillo == null) {
                    if (lista[indice].getNombre() == "MARTILLO") {
                        martillo = lista[indice]
                    }
                    indice++
                }

                if (martillo == null) {
                    val martilloCreado =
                        Articulo("MARTILLO", 3, Articulo.TipoArt.ARMA, "martillo", 1, 3)
                    martilloCreado.setIdArticulo(99999)
                    personaje.getMochila()!!.guardarArticulo(martilloCreado)
                } else {
                    martillo.sumaUnidades(1)
                }
                Toast.makeText(this, "Se ha creado un martillo", Toast.LENGTH_LONG).show()
                personaje.getMochila()!!.eliminarArticuloMonstruoGarra(1)
                personaje.getMochila()!!.eliminarArticuloMonstruoHierro(1)
                ImagenMartillo.visibility = View.VISIBLE
                ImagenHerrero.visibility = View.INVISIBLE
            } else {
                Toast.makeText(
                    this,
                    "No tienes suficinetes objetos para crear un martillo",
                    Toast.LENGTH_SHORT
                ).show()
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
                martillo.visibility = View.INVISIBLE
                yelmo.visibility = View.VISIBLE
                espada_infernal.visibility = View.VISIBLE
            }
        }


        yelmo.setOnClickListener {
            if (lingote >= 1) {
                var yelmo: Articulo? = null
                var indice = 0
                while (indice < lista.size && yelmo == null) {
                    if (lista[indice].getNombre() == "YELMO") {
                        yelmo = lista[indice]
                    }
                    indice++
                }

                if (yelmo == null) {
                    val yelmoCreado =
                        Articulo("YELMO", 2, Articulo.TipoArt.PROTECCION, "yelmo", 1, 3)
                    yelmoCreado.setIdArticulo(9229)
                    personaje.getMochila()!!.guardarArticulo(yelmoCreado)
                } else {
                    yelmo.sumaUnidades(1)
                }
                Toast.makeText(this, "Se ha creado un yelmo", Toast.LENGTH_LONG).show()
                personaje.getMochila()!!.eliminarArticuloMonstruoLingote(1)
                ImagenYelmo.visibility = View.VISIBLE
                ImagenHerrero.visibility = View.INVISIBLE
            } else {
                Toast.makeText(
                    this,
                    "No tienes suficinetes objetos para crear un yelmo",
                    Toast.LENGTH_SHORT
                ).show()
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
                martillo.visibility = View.VISIBLE
                yelmo.visibility = View.INVISIBLE
                espada_infernal.visibility = View.VISIBLE
            }
        }

        espada_infernal.setOnClickListener {
            if (garra >= 1 && pelo >= 1 && hierro >= 1) {
                var estada_infernal: Articulo? = null
                var indice = 0
                while (indice < lista.size && estada_infernal == null) {
                    if (lista[indice].getNombre() == "ESPADA") {
                        estada_infernal = lista[indice]
                    }
                    indice++
                }

                if (estada_infernal == null) {
                    val espadaCreada =
                        Articulo("ESPADA", 2, Articulo.TipoArt.ARMA, "espada_fuego", 1, 3)
                    espadaCreada.setIdArticulo(9555)
                    personaje.getMochila()!!.guardarArticulo(espadaCreada)
                } else {
                    estada_infernal.sumaUnidades(1)
                }
                Toast.makeText(this, "Se ha creado una espada infernal", Toast.LENGTH_LONG).show()
                personaje.getMochila()!!.eliminarArticuloMonstruoGarra(1)
                personaje.getMochila()!!.eliminarArticuloMonstruoHierro(1)
                personaje.getMochila()!!.eliminarArticuloMonstruoPelo(1)
                ImagenEspada.visibility = View.VISIBLE
                ImagenHerrero.visibility = View.INVISIBLE

            } else {
                Toast.makeText(
                    this,
                    "No tienes suficinetes objetos para crear una espada infernal",
                    Toast.LENGTH_SHORT
                ).show()
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
                martillo.visibility = View.VISIBLE
                yelmo.visibility = View.INVISIBLE
                espada_infernal.visibility = View.VISIBLE
            }
        }





        botonReparar.setOnClickListener {
            if (numeroUnidades == 0) {
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
            }
        }

        botonPelea.setOnClickListener {
            var intent = Intent(this, EnemigoActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            mediaplayer.pause()
            startActivity(intent)
        }

        botonMercader.setOnClickListener {
            var intent: Intent = Intent(this, MercaderActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            mediaplayer.pause()
        }

        marcharse.setOnClickListener {
            var intent = Intent(this, AventuraActivity::class.java)
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

            R.id.botonDialogFlow -> {
                var intent = Intent(this, Dialogflow::class.java)
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