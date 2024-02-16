package com.example.personajecreacion

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger

class ObjetoActivity : AppCompatActivity() {
    val log = Logger.getLogger("ObjectoActivity")
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        log.info("personaje obtenido $personaje")
        var dbHelper = ObjectosDataBase(applicationContext)
        val articulo = dbHelper.getArticuloAleatorio()
        val imageView = findViewById<ImageView>(R.id.ImagenObjeto)
        val textoInicio = findViewById<TextView>(R.id.textoPrincipio)
        val textoConirmacion = findViewById<TextView>(R.id.textoConfirmacion)
        val textoSinEspacio = findViewById<TextView>(R.id.textoSinEspacio)
        val cascoId = resources.getIdentifier(articulo.getImagen(), "drawable", packageName)
        val recoger: Button = findViewById(R.id.recoger)
        val siguiente: Button = findViewById(R.id.continuarOb)

        imageView.setImageResource(cascoId)

        textoConirmacion.setVisibility(View.INVISIBLE)
        textoSinEspacio.setVisibility(View.INVISIBLE)

        recoger.setOnClickListener {
            log.info("recogiendo objecto")
            if (personaje!!.getMochila()!!.guardarArticulo(articulo)) {
                textoConirmacion.visibility = View.VISIBLE
            } else {
                textoSinEspacio.visibility = View.VISIBLE
            }
        }

        siguiente.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}