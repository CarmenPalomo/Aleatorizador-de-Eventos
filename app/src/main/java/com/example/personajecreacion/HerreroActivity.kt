package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HerreroActivity : AppCompatActivity() {
    private lateinit var botonPelea : Button
    private lateinit var botonMercader : Button
    private lateinit var botonReparar : Button
    private lateinit var botonCrear : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herrero)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        botonCrear = findViewById(R.id.crear)
        botonReparar = findViewById(R.id.reparar)
        botonPelea = findViewById(R.id.pelea)
        botonMercader = findViewById(R.id.mercader)

        botonCrear.setOnClickListener {

        }

        botonReparar.setOnClickListener {

        }

        botonPelea.setOnClickListener {

        }

        botonMercader.setOnClickListener {
            var intent : Intent = Intent(this,AventuraActivity::class.java)
            startActivity(intent)
        }


    }
}