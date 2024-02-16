package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import java.util.logging.Logger

class AventuraActivity : AppCompatActivity() {
    private val log = Logger.getLogger("AventuraActivity")


    val personaje: Personaje? = intent.getParcelableExtra("Personaje")
    val personajeDataBase = PersonajeDataBase(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aventura)


        log.info("personaje obtenido $personaje")
        var dado: ImageButton = findViewById(R.id.dado)

        dado.setOnClickListener {
            val num = (1..4).random()
            val intent: Intent
            when (num) {
                1 -> {
                    intent = Intent(this, ObjetoActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    startActivity(intent)
                }

                2 -> {
                    intent = Intent(this, CiudadActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    startActivity(intent)
                }

                3 -> {
                    intent = Intent(this, MercaderActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    startActivity(intent)
                }

                4 -> {
                    intent = Intent(this, EnemigoActivity::class.java)
                    intent.putExtra("Personaje", personaje)
                    startActivity(intent)
                }
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
            else -> super.onOptionsItemSelected(item)
        }
    }

}