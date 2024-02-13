package com.example.personajecreacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PeleaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelea)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }
}