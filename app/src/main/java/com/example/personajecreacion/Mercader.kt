package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Mercader : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)

        val comerciar: Button = findViewById(R.id.comerciar)
        val continuar : Button = findViewById(R.id.continuarM)

        comerciar.setOnClickListener {
            val intent = Intent(this,Blanco::class.java)
            startActivity(intent)
        }

        continuar.setOnClickListener {
            val intent = Intent(this,Aventura::class.java)
            startActivity(intent)
        }

    }
}