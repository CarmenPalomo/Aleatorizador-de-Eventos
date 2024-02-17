package com.example.personajecreacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class PeleaActivity : AppCompatActivity() {

    private lateinit var vidaMonstruo: ProgressBar
    private lateinit var vidaJugador: ProgressBar
    private lateinit var atacarButton: Button

    private lateinit var  personaje: Personaje
    private lateinit var monstruo: Monstruo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelea)

        personaje = intent.getParcelableExtra("Personaje")!!
        monstruo = intent.getParcelableExtra("Monstruo") !!

        vidaMonstruo = findViewById(R.id.vidaMonstruo)
        vidaJugador = findViewById(R.id.vidaJugador)
        atacarButton = findViewById(R.id.atacarButton)

        atacarButton.setOnClickListener {
            performAttack()
        }
    }


    private fun performAttack() {

        if (monstruo!!.getSalud() > 0 && personaje!! .getSalud()!! > 0) {
            reduceMonsterHealth()

            if (monstruo.getSalud() > 0) {
                reducePlayerHealth()
            }
        }

        checkBattleResult()
    }

    private fun reduceMonsterHealth() {
        var currentHealth: Int = vidaMonstruo.progress
        currentHealth = monstruo?.getSalud()!! - personaje?.getAtaque()!!
        monstruo?.setSalud(currentHealth)
        val newHealth = monstruo?.getSalud()
        if (newHealth != null) {
            vidaMonstruo.progress = if (newHealth < 0) 0 else newHealth
        }
    }

    private fun reducePlayerHealth() {
        var currentHealth = vidaJugador.progress
        currentHealth = personaje?.getSalud()!! - monstruo?.getAtaque()!!
        personaje.setSalud(currentHealth)
        val newHealth = personaje?.getSalud()
        if (newHealth != null) {
            vidaJugador.progress = if (newHealth < 0) 0 else newHealth
        }
    }

    private fun checkBattleResult() {
        if (monstruo?.getSalud()!! <= 0) {
            Toast.makeText(this, "Has derrotado al monstruo", Toast.LENGTH_SHORT).show()
        } else if (personaje?.getSalud()!! <= 0) {
            Toast.makeText(this, "El monstruo te ha derrotado", Toast.LENGTH_SHORT).show()
        }
    }
}