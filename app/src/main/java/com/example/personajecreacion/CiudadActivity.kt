package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CiudadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudad)

        val entrar: Button = findViewById(R.id.entrar)
        val continuar : Button = findViewById(R.id.continuar)

        entrar.setOnClickListener {
            val intent = Intent(this,Blanco::class.java)
            startActivity(intent)
        }

        continuar.setOnClickListener {
            val intent = Intent(this,AventuraActivity::class.java)
            startActivity(intent)
        }

    }
}