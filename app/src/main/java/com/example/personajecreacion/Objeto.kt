package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.logging.Logger

class Objeto : AppCompatActivity() {
    val log = Logger.getLogger("ObjectoActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        val personaje : Personaje? = intent.getParcelableExtra("Personaje")

        var dbHelper  = ObjectosDataBase(applicationContext)
        val articulo = dbHelper.getArticuloAleatorio()
        val imageView = findViewById<ImageView>(R.id.ImagenObjeto)

        val cascoId = resources.getIdentifier(articulo.getImagen(), "drawable", packageName)
        imageView.setImageResource(cascoId)
        val recoger: Button = findViewById(R.id.recoger)
        val siguiente: Button = findViewById(R.id.continuarOb)


        recoger.setOnClickListener {
            log.info("recogiendo objecto")
            if (personaje!!.getMochila()!!.espacio > 0) {
                personaje.getMochila()!!.guardarArticulo(articulo)
                val intent = Intent(this, Aventura::class.java)
                intent.putExtra("Personaje", personaje)
                startActivity(intent)
            }
        }

        siguiente.setOnClickListener {
            val intent = Intent(this, Aventura::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }
    }
}