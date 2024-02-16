package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EnemigoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)

        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        val luchar: Button = findViewById(R.id.luchar)
        val huir : Button = findViewById(R.id.huir)

        luchar.setOnClickListener {
            val intent = Intent(this,PeleaActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }

        huir.setOnClickListener {
            val intent = Intent(this,AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }
    }
}