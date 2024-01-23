package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Mercader : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercader)

        val comerciar: Button = findViewById(R.id.comerciar)
        val continuar: Button = findViewById(R.id.continuarM)
        val comprar: Button = findViewById(R.id.comprar)
        val vender: Button = findViewById(R.id.vender)
        val cancelar: Button = findViewById(R.id.cancelar)

        comerciar.setOnClickListener {
        //val intent = Intent(this,Blanco::class.java)
            // startActivity(intent)
            comerciar.setVisibility(View.INVISIBLE)
            continuar.setVisibility(View.INVISIBLE)
            comprar.setVisibility(View.VISIBLE)
            vender.setVisibility(View.VISIBLE)
            cancelar.setVisibility(View.VISIBLE)
        }

        continuar.setOnClickListener {
            val intent = Intent(this, Aventura::class.java)
            startActivity(intent)
        }

    }
}