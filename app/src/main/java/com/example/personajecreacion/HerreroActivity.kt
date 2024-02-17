package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button

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

        var lista = personaje.getMochila()?.getArticulos()
        var lista_esp: ArrayList<Articulo>? = null

        botonPelea.visibility = View.INVISIBLE
        lista?.forEach { articulo ->

            if (articulo.getNombre() == "OBJETOMONSTUO"){
                lista_esp!!.add(articulo)
            }
        }

        botonCrear.setOnClickListener {
            if (lista_esp == null){
                botonPelea.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE

            }else{

            }
        }

        botonReparar.setOnClickListener {
            if (lista_esp == null){
                botonPelea.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
            }else{

            }
        }

        botonPelea.setOnClickListener {
            var intent = Intent(this, EnemigoActivity::class.java)
            startActivity(intent)
        }

        botonMercader.setOnClickListener {
            var intent : Intent = Intent(this,AventuraActivity::class.java)
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