package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class HerreroActivity : AppCompatActivity() {
    private lateinit var botonPelea : Button
    private lateinit var botonMercader : Button
    private lateinit var botonReparar : Button
    private lateinit var botonCrear : Button
    private lateinit var personaje: Personaje
    private lateinit var personajeDataBase: PersonajeDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herrero)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        personajeDataBase = PersonajeDataBase(applicationContext)
        personaje = intent.getParcelableExtra("Personaje")!!
        botonCrear = findViewById(R.id.crear)
        botonReparar = findViewById(R.id.reparar)
        botonPelea = findViewById(R.id.pelea)
        botonMercader = findViewById(R.id.mercader)
        var ImagenMartillo : ImageView = findViewById(R.id.ImagenMartillo)
        var ImagenHerrero : ImageView = findViewById(R.id.ImagenHerrero)

        var lista = personaje.getMochila()?.getArticulos()

        botonPelea.visibility = View.INVISIBLE

        var numeroUnidades = 0
        for(articulo in lista!!){
            if (articulo.getTipo() == Articulo.TipoArt.OBJETOMONSTRUO){
                numeroUnidades += articulo.getUnidades()
            }
        }


        botonCrear.setOnClickListener {
            if (numeroUnidades == 0){
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE

            }else{
                if (numeroUnidades >= 3){

                    personaje.getMochila()!!.eliminarArticulosMonstruo(3)
                    ImagenMartillo.visibility = View.VISIBLE
                    ImagenHerrero.visibility = View.INVISIBLE
                    Toast.makeText(this, "Se ha creado un martillo", Toast.LENGTH_SHORT).show()
                    personaje!!.getMochila()!!.guardarArticulo(Articulo("MARTILLO",3,Articulo.TipoArt.ARMA,"martillo",3,3))

                }else{
                    Toast.makeText(this, "No tienes suficinetes objetos para crear un arma", Toast.LENGTH_SHORT).show()
                    botonPelea.visibility = View.VISIBLE
                    botonMercader.visibility = View.VISIBLE
                    botonCrear.visibility = View.INVISIBLE
                    botonReparar.visibility = View.INVISIBLE
                }


            }
        }

        botonReparar.setOnClickListener {
            if (numeroUnidades == 0){
                botonPelea.visibility = View.VISIBLE
                botonMercader.visibility = View.VISIBLE
                botonCrear.visibility = View.INVISIBLE
                botonReparar.visibility = View.INVISIBLE
            }else{



            }
        }

        botonPelea.setOnClickListener {
            var intent = Intent(this, EnemigoActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }

        botonMercader.setOnClickListener {
            var intent : Intent = Intent(this,MercaderActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.botonGuardar -> {
                personajeDataBase.actualizarPersonaje(personaje)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}