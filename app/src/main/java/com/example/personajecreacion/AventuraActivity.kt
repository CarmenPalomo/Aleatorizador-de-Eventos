package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class AventuraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aventura)

        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        var dado: ImageButton = findViewById(R.id.dado)

        dado.setOnClickListener {
            var num = (1..4).random()
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