package com.example.personajecreacion

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import android.view.View

class PeleaActivity : AppCompatActivity() {

    private lateinit var vidaMonstruo: ProgressBar
    private lateinit var vidaJugador: ProgressBar
    private lateinit var atacarButton: Button

    private lateinit var  personaje: Personaje
    private lateinit var monstruo: Monstruo
    private lateinit var imageView : ImageView
    private lateinit var continuar : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelea)

        personaje = intent.getParcelableExtra("Personaje")!!
        monstruo = intent.getParcelableExtra("Monstruo") !!

        vidaMonstruo = findViewById(R.id.vidaMonstruo)
        vidaJugador = findViewById(R.id.vidaJugador)
        atacarButton = findViewById(R.id.atacarButton)
        imageView = findViewById(R.id.imagenPersonaje)
        continuar = findViewById(R.id.continuar)

        atacarButton.setOnClickListener {
            performAttack()
        }

        continuar.setOnClickListener {
            val intent = Intent(this, AventuraActivity::class.java)
            intent.putExtra("Personaje", personaje)
            startActivity(intent)
        }

        if (personaje.getClase() != null && personaje.getRaza() != null && personaje.getEstadoVital() != null) {
            when(personaje.getRaza()) {
                "Humano"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.humano_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.humano_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.humano_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.humano_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.humano_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.humano_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.humano_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.humano_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.humano_guerrero_joven)
                                }
                            }
                        }
                    }
                }
                "Elfo"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.elfo_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.elfo_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.elfo_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.elfo_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.elfo_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.elfo_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.elfo_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.elfo_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.elfo_guerrero_joven)
                                }
                            }
                        }
                    }
                }
                "Enano"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.enano_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.enano_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.enano_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.enano_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.enano_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.enano_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.enano_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.enano_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.enano_guerrero_joven)
                                }
                            }
                        }
                    }
                }

                "Maldito"->{
                    when(personaje.getClase()){
                        "Brujo"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.maldito_brujo_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.maldito_brujo_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.maldito_brujo_adolescente)
                                }
                            }
                        }
                        "Mago"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.maldito_mago_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.maldito_mago_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.maldito_mago_adolescente)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(personaje.getEstadoVital()){
                                "Anciano"->{
                                    imageView.setImageResource(R.drawable.maldito_guerrero_anciano)
                                }
                                "Adulto"->{
                                    imageView.setImageResource(R.drawable.maldito_guerrero_adulto)
                                }
                                "Joven"->{
                                    imageView.setImageResource(R.drawable.maldito_guerrero_adolescente)
                                }
                            }
                        }
                    }
                }

            }
        }
    }


    private fun performAttack() {

        if (monstruo.getSalud() > 0 && personaje .getSalud()!! > 0) {
            reduceMonsterHealth()

            if (monstruo.getSalud() > 0) {
                reducePlayerHealth()
            }
        }

        checkBattleResult()
    }

    private fun reduceMonsterHealth() {
        var currentHealth: Int = vidaMonstruo.progress
        currentHealth = monstruo.getSalud() - personaje.getAtaque()!!
        monstruo.setSalud(currentHealth)
        val newHealth = monstruo.getSalud()
        if (newHealth != null) {
            vidaMonstruo.progress = if (newHealth < 0) 0 else newHealth
        }
    }

    private fun reducePlayerHealth() {
        var currentHealth = vidaJugador.progress
        currentHealth = personaje.getSalud()!! - monstruo.getAtaque()
        personaje.setSalud(currentHealth)
        val newHealth = personaje.getSalud()
        if (newHealth != null) {
            vidaJugador.progress = if (newHealth < 0) 0 else newHealth
        }
    }

    private fun checkBattleResult() {
        if (monstruo.getSalud() <= 0) {
            Toast.makeText(this, "Has derrotado al monstruo", Toast.LENGTH_SHORT).show()
            atacarButton.visibility = View.INVISIBLE
            continuar.visibility= View.VISIBLE
        } else if (personaje.getSalud()!! <= 0) {
            Toast.makeText(this, "El monstruo te ha derrotado", Toast.LENGTH_SHORT).show()
            atacarButton.visibility = View.INVISIBLE
            continuar.visibility= View.VISIBLE
        }
    }
}