package com.example.personajecreacion

import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger

class ObjetoActivity : AppCompatActivity() {
    val log = Logger.getLogger("ObjectoActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objeto)

        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        var dbHelper = ObjectosDataBase(applicationContext)
        val articulo = dbHelper.getArticuloAleatorio()
        val imageView = findViewById<ImageView>(R.id.ImagenObjeto)
        val textoInicio = findViewById<TextView>(R.id.textoPrincipio)
        val textoConirmacion = findViewById<TextView>(R.id.textoConfirmacion)
        val textoSinEspacio = findViewById<TextView>(R.id.textoSinEspacio)
        val cascoId = resources.getIdentifier(articulo.getImagen(), "drawable", packageName)
        val recoger: Button = findViewById(R.id.recoger)
        val siguiente: Button = findViewById(R.id.continuarOb)


        imageView.setImageResource(cascoId)


        recoger.setOnClickListener {
            textoConirmacion.setVisibility(View.INVISIBLE)
            textoSinEspacio.setVisibility(View.INVISIBLE)
            log.info("recogiendo objecto")
            if (personaje!!.getMochila()!!.espacio > 0) {
                personaje.getMochila()!!.guardarArticulo(articulo)
                textoConirmacion.setVisibility(View.VISIBLE)
                val intent = Intent(this, AventuraActivity::class.java)


            } else {
                textoSinEspacio.setVisibility(View.VISIBLE)
            }
        }

        siguiente.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }
    }
}