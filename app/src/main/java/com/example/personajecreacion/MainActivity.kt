package com.example.personajecreacion

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // Creación de variables para saber si han sido seleccionadas o no en el spinner
    private var opcionSpinnerClase: String? = null
    private var opcionSpinnerRaza: String? = null
    private var opcionSpinnerEdad: String? = null
    private var opcionImagen: Int = 0
    private lateinit var nombreEditText: EditText
    private lateinit var mediaplayer : MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creacion de variables
        nombreEditText = findViewById(R.id.nombre)
        val userId = intent.getStringExtra("userId")
        val botonSiguiente: Button = findViewById(R.id.boton_siguiente)
        val spinnerRaza: Spinner = findViewById(R.id.spinner_raza)
        val spinnerClase: Spinner = findViewById(R.id.spinner_clase)
        val spinnerEdad: Spinner = findViewById(R.id.spinner_edad)
        mediaplayer = MediaPlayer.create(this, R.raw.sinfonia_molto_allegro)
        mediaplayer.setLooping(true);
        nombreEditText = findViewById(R.id.nombre)

        // Se esta asociando a quien tiene que llamar el Spinner cuando ocurre el evento onItemSelected.
        spinnerRaza.onItemSelectedListener = this
        spinnerEdad.onItemSelectedListener = this
        spinnerClase.onItemSelectedListener = this



        botonSiguiente.setOnClickListener {
            val intent = Intent(this@MainActivity, MostrarDatosActivity::class.java)
            intent.putExtra("raza",opcionSpinnerRaza)
            intent.putExtra("clase",opcionSpinnerClase)
            intent.putExtra("edad",opcionSpinnerEdad)
            intent.putExtra("nombre",nombreEditText.text.toString())
            intent.putExtra("userId", userId)
            intent.putExtra("imagen", opcionImagen)

            intent.putExtra("musicaMin", mediaplayer.currentPosition)
            intent.putExtra("estadoM", mediaplayer.isPlaying)
            startActivity(intent)
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var imageView: ImageView = findViewById(R.id.imageView2)
        when (parent?.id) {
            R.id.spinner_clase -> {
                opcionSpinnerClase = parent.getItemAtPosition(position) as String
            }
            R.id.spinner_raza -> {
                opcionSpinnerRaza = parent.getItemAtPosition(position) as String
            }
            R.id.spinner_edad -> {
                opcionSpinnerEdad = parent.getItemAtPosition(position) as String
            }
        }

        if (opcionSpinnerClase != null && opcionSpinnerRaza != null && opcionSpinnerEdad != null) {
            when(opcionSpinnerRaza) {
                "Humano"->{
                    when(opcionSpinnerClase){
                        "Brujo"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.humano_brujo_anciano
                                    imageView.setImageResource(R.drawable.humano_brujo_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.humano_brujo_adulto
                                    imageView.setImageResource(R.drawable.humano_brujo_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.humano_brujo_joven
                                    imageView.setImageResource(R.drawable.humano_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.humano_mago_anciano
                                    imageView.setImageResource(R.drawable.humano_mago_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.humano_mago_adulto
                                    imageView.setImageResource(R.drawable.humano_mago_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.humano_mago_joven
                                    imageView.setImageResource(R.drawable.humano_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.humano_guerrero_anciano
                                    imageView.setImageResource(R.drawable.humano_guerrero_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.humano_guerrero_adulto
                                    imageView.setImageResource(R.drawable.humano_guerrero_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.humano_guerrero_joven
                                    imageView.setImageResource(R.drawable.humano_guerrero_joven)
                                }
                            }
                        }
                    }
                }
                "Elfo"->{
                    when(opcionSpinnerClase){
                        "Brujo"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.elfo_brujo_anciano
                                    imageView.setImageResource(R.drawable.elfo_brujo_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.elfo_brujo_adulto
                                    imageView.setImageResource(R.drawable.elfo_brujo_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.elfo_brujo_joven
                                    imageView.setImageResource(R.drawable.elfo_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.elfo_mago_anciano
                                    imageView.setImageResource(R.drawable.elfo_mago_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.elfo_mago_adulto
                                    imageView.setImageResource(R.drawable.elfo_mago_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.elfo_mago_joven
                                    imageView.setImageResource(R.drawable.elfo_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.elfo_guerrero_anciano
                                    imageView.setImageResource(R.drawable.elfo_guerrero_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.elfo_guerrero_adulto
                                    imageView.setImageResource(R.drawable.elfo_guerrero_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.elfo_guerrero_joven
                                    imageView.setImageResource(R.drawable.elfo_guerrero_joven)
                                }
                            }
                        }
                    }
                }
                "Enano"->{
                    when(opcionSpinnerClase){
                        "Brujo"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.enano_brujo_anciano
                                    imageView.setImageResource(R.drawable.enano_brujo_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.enano_brujo_adulto
                                    imageView.setImageResource(R.drawable.enano_brujo_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.enano_brujo_joven
                                    imageView.setImageResource(R.drawable.enano_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.enano_mago_anciano
                                    imageView.setImageResource(R.drawable.enano_mago_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.enano_mago_adulto
                                    imageView.setImageResource(R.drawable.enano_mago_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.enano_mago_joven
                                    imageView.setImageResource(R.drawable.enano_mago_joven)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.enano_guerrero_anciano
                                    imageView.setImageResource(R.drawable.enano_guerrero_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.enano_guerrero_adulto
                                    imageView.setImageResource(R.drawable.enano_guerrero_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.enano_guerrero_joven
                                    imageView.setImageResource(R.drawable.enano_guerrero_joven)
                                }
                            }
                        }
                    }
                }

                "Maldito"->{
                    when(opcionSpinnerClase){
                        "Brujo"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.maldito_brujo_anciano
                                    imageView.setImageResource(R.drawable.maldito_brujo_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.maldito_brujo_adulto
                                    imageView.setImageResource(R.drawable.maldito_brujo_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.maldito_brujo_adolescente
                                    imageView.setImageResource(R.drawable.maldito_brujo_adolescente)
                                }
                            }
                        }
                        "Mago"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.maldito_mago_anciano
                                    imageView.setImageResource(R.drawable.maldito_mago_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.maldito_mago_adulto
                                    imageView.setImageResource(R.drawable.maldito_mago_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.maldito_mago_adolescente
                                    imageView.setImageResource(R.drawable.maldito_mago_adolescente)
                                }
                            }
                        }
                        "Guerrero"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = R.drawable.maldito_guerrero_anciano
                                    imageView.setImageResource(R.drawable.maldito_guerrero_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = R.drawable.maldito_guerrero_adulto
                                    imageView.setImageResource(R.drawable.maldito_guerrero_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = R.drawable.maldito_guerrero_adolescente
                                    imageView.setImageResource(R.drawable.maldito_guerrero_adolescente)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.suenaM -> {
                mediaplayer.start()
                true
            }
            R.id.paraM ->{
                mediaplayer.pause()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    

}