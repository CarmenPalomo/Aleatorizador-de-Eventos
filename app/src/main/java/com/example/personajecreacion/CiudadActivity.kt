package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView

class CiudadActivity : AppCompatActivity() {
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        val entrar: Button = findViewById(R.id.entrar)
        val continuar: Button = findViewById(R.id.continuar)
        val taberna: Button = findViewById(R.id.taberna)
        val herrero: Button = findViewById(R.id.herrero)
        val textEntrada: TextView = findViewById(R.id.textoEntrada)

        entrar.setOnClickListener {
            var num = (1..2).random()
            val intent: Intent
            when (num) {
                1 -> {
                    intent = Intent(this, GuardiaActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    startActivity(intent)
                }

                2 -> {
                    entrar.visibility = View.INVISIBLE
                    continuar.visibility = View.INVISIBLE
                    taberna.visibility = View.VISIBLE
                    textEntrada.visibility = View.VISIBLE
                    herrero.visibility = View.VISIBLE

                }
            }

        }

        taberna.setOnClickListener {
            val intent = Intent(this, TabernaActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)

        }

        herrero.setOnClickListener {
            val intent = Intent(this, HerreroActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)

        }


        continuar.setOnClickListener {
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