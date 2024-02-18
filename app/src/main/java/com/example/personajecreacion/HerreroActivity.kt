package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class HerreroActivity : AppCompatActivity() {
    private lateinit var botonPelea : Button
    private lateinit var botonMercader : Button
    private lateinit var botonReparar : Button
    private lateinit var botonCrear : Button
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase

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
        var ImagenMartillo : ImageView = findViewById(R.id.ImagenMartillo)
        var ImagenYelmo : ImageView = findViewById(R.id.ImagenYelmo)
        var ImagenEspada : ImageView = findViewById(R.id.ImagenEspada)
        var ImagenHerrero : ImageView = findViewById(R.id.ImagenHerrero)
        var marcharse: Button = findViewById(R.id.marcharse)

        var lista = personaje.getMochila()?.getArticulos()

        var garra = 0
        var pelo = 0
        var bronce = 0
        var hierro = 0
        var lingote = 0

        botonPelea.visibility = View.INVISIBLE

        var numeroUnidades = 0
        for(articulo in lista!!){
            if (articulo.getTipo() == Articulo.TipoArt.OBJETOMONSTRUO){
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
            }
            else if(lingote >= 1){
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
            }else if(garra >= 1 && pelo >= 1 && hierro >= 1){
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

            } else{
                Toast.makeText(
                    this,
                    "No tienes suficinetes objetos para crear un arma",
                    Toast.LENGTH_SHORT
                ).show()
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
            }
        }

        botonReparar.setOnClickListener {
            if (numeroUnidades == 0){
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
            }
        }

        botonPelea.setOnClickListener {
            var intent = Intent(this, EnemigoActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }

        botonMercader.setOnClickListener {
            var intent : Intent = Intent(this,MercaderActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }

        marcharse.setOnClickListener {
            var intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}