package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Objeto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        val recoger:Button = findViewById(R.id.recoger)
        val siguiente : Button = findViewById(R.id.continuarOb)

        recoger.setOnClickListener {
            val intent = Intent(this,Aventura::class.java)
            startActivity(intent)
        }

        siguiente.setOnClickListener {
            val intent = Intent(this,Aventura::class.java)
            startActivity(intent)
        }

    }
}