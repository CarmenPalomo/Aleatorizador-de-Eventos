package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class EnemigoActivity : AppCompatActivity() {
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        val monstruo : Monstruo = Monstruo("Belzebu", 1)
        val luchar: Button = findViewById(R.id.luchar)
        val huir : Button = findViewById(R.id.huir)

        luchar.setOnClickListener {
            val intent = Intent(this,PeleaActivity::class.java)
            intent.putExtra("Personaje", personaje)
            intent.putExtra("Monstruo", monstruo)
            startActivity(intent)
        }

        huir.setOnClickListener {
            val intent = Intent(this,AventuraActivity::class.java)
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