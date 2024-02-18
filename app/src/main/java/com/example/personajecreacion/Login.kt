package com.example.personajecreacion


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.ContentValues
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import java.util.logging.Logger

class Login : AppCompatActivity() {
    private val log: Logger = Logger.getLogger("Login")
    private lateinit var crearCuenta: Button
    private lateinit var continua: Button
    private lateinit var auth: FirebaseAuth

    private lateinit var email: EditText
    private lateinit var pass: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        // Evento personalizado para Google Analytics
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de mensaje completa")
        analytics.logEvent("InitScreen", bundle)

        acceder()

    }


    private fun acceder() {
        val personajeDataBase = PersonajeDataBase(applicationContext)
        crearCuenta = findViewById(R.id.crearCuenta)
        continua = findViewById(R.id.continuarInicio)

        email = findViewById(R.id.editTextTextEmailAddress)
        pass = findViewById(R.id.editTextTextPassword)
        crearCuenta.isClickable = true

        continua.setOnClickListener {
            if (email.text.isNotEmpty() && pass.text.isNotEmpty()) {

                auth.signInWithEmailAndPassword(
                    email.text.toString(),
                    pass.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val usuario = auth.currentUser
                        var siguienteIntent: Intent? = null
                        val personajeRegistrado = personajeDataBase.getPersonaje(usuario!!.uid)

                        if (personajeRegistrado != null) {
                            siguienteIntent = Intent(this, AventuraActivity::class.java)
                            siguienteIntent.putExtra("Personaje", personajeRegistrado)
                        } else {
                            siguienteIntent = Intent(this, MainActivity::class.java)
                        }

                        siguienteIntent.putExtra("userId", usuario.uid)
                        log.info("usuario con id ${usuario.uid}")
                        startActivity(siguienteIntent)
                    } else {
                        showAlert()
                    }
                }
            }
        }
        crearCuenta.setOnClickListener {
            val registrarse = Intent(this, Register::class.java)
            startActivity(registrarse)
        }
    }

    private fun showAlert() {
        Log.d(ContentValues.TAG, "Error creando nuevo usuario")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en el login de usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}