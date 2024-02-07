package com.example.personajecreacion


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var usuariosRef : DatabaseReference

    private lateinit var correo : EditText
    private lateinit var nombre : EditText
    private lateinit var contra : EditText
    private lateinit var continua : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        usuariosRef = database.getReference("usuarios")

        correo = findViewById(R.id.mail)
        nombre = findViewById(R.id.usuario)
        contra = findViewById(R.id.contra)
        continua = findViewById(R.id.continuarInicio)

        continua.setOnClickListener {
            if (correo.text.isNotEmpty() && contra.text.isNotEmpty() && nombre.text.isNotEmpty()) {
                Log.d("Prueba", "Creado nuevo usuario")
                auth.createUserWithEmailAndPassword(
                    correo.text.toString(),
                    contra.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        datosUsuario(correo.text.toString(),nombre.text.toString())
                        val registrado = Intent(this, Login::class.java)
                        startActivity(registrado)
                    } else {
                        showAlert("Error creando el usuario")
                    }
                }
            }
        }
    }

    private fun showAlert(mensaje : String){
        //Log.d(TAG, "Error creando nuevo usuario")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun datosUsuario (correo : String, nombre : String){
        val usuarioActual : FirebaseUser? = auth.currentUser
        if (usuarioActual !=null) {
            // insertamos los datos del usuario actual en nuestra Base de Datos
            val user = Usuario (correo, nombre)
            usuariosRef.child(usuarioActual.uid).setValue(user)
        } else {

        }

    }
}