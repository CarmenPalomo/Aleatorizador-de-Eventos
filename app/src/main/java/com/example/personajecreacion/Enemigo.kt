package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Enemigo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enemigo)

        val luchar: Button = findViewById(R.id.luchar)
        val huir : Button = findViewById(R.id.huir)

        luchar.setOnClickListener {
            val intent = Intent(this,Blanco::class.java)
            startActivity(intent)
        }

        huir.setOnClickListener {
            val intent = Intent(this,Aventura::class.java)
            startActivity(intent)
        }
    }
}