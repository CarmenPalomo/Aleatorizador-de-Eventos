package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import java.util.logging.Logger

class AventuraActivity : AppCompatActivity() {
    private val log = Logger.getLogger("AventuraActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aventura)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        log.info("personaje obtenido $personaje")
        var dado: ImageButton = findViewById(R.id.dado)

        dado.setOnClickListener {
            val num = if ((1..4).random() == 1) 1 else 3
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
}