package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class TabernaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taberna)


        val personaje: Personaje? = intent.getParcelableExtra("Personaje")
        val entrar : Button = findViewById(R.id.entrar)
        val marcharse : Button = findViewById(R.id.irse)
        val pagar : Button = findViewById(R.id.pagar)
        val textoPagar : TextView = findViewById(R.id.textoPagar)
        val textoPrecio : TextView = findViewById(R.id.textoPrecio)
        val textoNoPagar : TextView = findViewById(R.id.textoNoPagar)


        entrar.setOnClickListener {
            entrar.visibility = View.INVISIBLE
            pagar.visibility = View.VISIBLE
            marcharse.visibility = View.VISIBLE
            textoPagar.visibility = View.INVISIBLE
            textoNoPagar.visibility = View.INVISIBLE
            textoPrecio.visibility = View.VISIBLE

        }

        marcharse.setOnClickListener {
            val intent = Intent(this,AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }

        pagar.setOnClickListener {

            if (personaje!!.getMochila()!!.tieneOro()){

                personaje.getMochila()!!.restarDinero(5)
                entrar.visibility = View.INVISIBLE
                pagar.visibility = View.INVISIBLE
                marcharse.visibility = View.VISIBLE
                textoPagar.visibility = View.VISIBLE
                textoNoPagar.visibility = View.INVISIBLE
                textoPrecio.visibility = View.INVISIBLE

            }else{

                entrar.visibility = View.INVISIBLE
                pagar.visibility = View.INVISIBLE
                marcharse.visibility = View.VISIBLE
                textoPagar.visibility = View.INVISIBLE
                textoNoPagar.visibility = View.VISIBLE
                textoPrecio.visibility = View.INVISIBLE

            }

        }




    }
}