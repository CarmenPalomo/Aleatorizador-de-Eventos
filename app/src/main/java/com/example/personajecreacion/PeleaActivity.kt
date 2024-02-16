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
    private var vidaJ : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelea)

        vidaMonstruo = findViewById(R.id.vidaMonstruo)
        vidaJugador = findViewById(R.id.vidaJugador)
        atacarButton = findViewById(R.id.atacarButton)

            atacarButton.setOnClickListener {
                performAttack()
            }
    }


    private fun performAttack() {

        if (monster.getSalud() > 0 && player.getSalud() > 0) {
            reduceMonsterHealth()

            if (monster.getSalud() > 0) {
                reducePlayerHealth()
            }
        }

        checkBattleResult()
    }

    private fun reduceMonsterHealth() {
        val currentHealth = vidaMonstruo.progress
        monster.setSalud() = monster.getSalud() - player.damage
        val newHealth = monster.getSalud()
        vidaMonstruo.progress = if (newHealth < 0) 0 else newHealth
    }

    private fun reducePlayerHealth() {
        val currentHealth = vidaJugador.progress
        player.setSalud() = player.getSalud() - monster.getDamage()
        val newHealth = player.getSalud()
        vidaJugador.progress = if (newHealth < 0) 0 else newHealth
    }

    private fun checkBattleResult() {
        if (monster.getSalud() <= 0) {
            Toast.makeText(this, "Has derrotado al monstruo", Toast.LENGTH_SHORT).show()
        } else if (player.setSalud() <= 0) {
            Toast.makeText(this, "El monstruo te ha derrotado", Toast.LENGTH_SHORT).show()
        }
    }
}