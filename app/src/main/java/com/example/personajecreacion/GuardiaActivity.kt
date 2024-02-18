package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class GuardiaActivity : AppCompatActivity() {
    private lateinit var boton: Button
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardia)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        boton = findViewById(R.id.botonEchado)

        boton.setOnClickListener {
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