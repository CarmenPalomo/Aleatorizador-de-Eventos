package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GuardiaActivity : AppCompatActivity() {
    private lateinit var boton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guardia)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        boton = findViewById(R.id.botonEchado)

        boton.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
            startActivity(intent)
        }
    }
}