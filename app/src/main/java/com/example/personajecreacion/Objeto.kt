package com.example.personajecreacion

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import java.io.File

class Objeto : AppCompatActivity() {
    lateinit var dbHelper: DataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        dbHelper = DataBaseHelper(baseContext)
        val articulo = dbHelper.getArticuloAleatorio()
        val imageView = findViewById<ImageView>(R.id.ImagenObjeto)

        imageView.setImageURI()
        val recoger: Button = findViewById(R.id.recoger)
        val siguiente: Button = findViewById(R.id.continuarOb)


        recoger.setOnClickListener {
            val intent = Intent(this, Aventura::class.java)
            startActivity(intent)


        }

        siguiente.setOnClickListener {
            val intent = Intent(this, Aventura::class.java)
            startActivity(intent)
        }
    }
}