package com.example.personajecreacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // Creación de variables para saber si han sido seleccionadas o no en el spinner
    private var opcionSpinnerClase: String? = null
    private var opcionSpinnerRaza: String? = null
    private var opcionSpinnerEdad: String? = null
    private lateinit var opcionImagen: String
    private lateinit var nombreEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creacion de variables
        nombreEditText = findViewById(R.id.nombre)
        val boton: Button = findViewById(R.id.boton_siguiente)
        val spinnerRaza: Spinner = findViewById(R.id.spinner_raza)
        val spinnerClase: Spinner = findViewById(R.id.spinner_clase)
        val spinnerEdad: Spinner = findViewById(R.id.spinner_edad)



        // Se esta asociando a quien tiene que llamar el Spinner cuando ocurre el evento onItemSelected.
        spinnerRaza.onItemSelectedListener = this
        spinnerEdad.onItemSelectedListener = this
        spinnerClase.onItemSelectedListener = this

        nombreEditText = findViewById(R.id.nombre)

        boton.setOnClickListener {
            val intent = Intent(this@MainActivity, MostrarDatos::class.java)
            intent.putExtra("raza",opcionSpinnerRaza)
            intent.putExtra("clase",opcionSpinnerClase)
            intent.putExtra("edad",opcionSpinnerEdad)
            intent.putExtra("nombre",nombreEditText.text.toString())
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
                            when(opcionSpinnerEdad){
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
                            when(opcionSpinnerEdad){
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
                    when(opcionSpinnerClase){
                        "Brujo"->{
                            when(opcionSpinnerEdad){
                                "Anciano"->{
                                    opcionImagen = "elfo_brujo_anciano"
                                    imageView.setImageResource(R.drawable.elfo_brujo_anciano)
                                }
                                "Adulto"->{
                                    opcionImagen = "elfo_brujo_adulto"
                                    imageView.setImageResource(R.drawable.elfo_brujo_adulto)
                                }
                                "Joven"->{
                                    opcionImagen = "elfo_brujo_joven"
                                    imageView.setImageResource(R.drawable.elfo_brujo_joven)
                                }
                            }
                        }
                        "Mago"->{
                            when(opcionSpinnerEdad){
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
                            when(opcionSpinnerEdad){
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
                    when(opcionSpinnerClase){
                        "Brujo"->{
                            when(opcionSpinnerEdad){
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
                            when(opcionSpinnerEdad){
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
                            when(opcionSpinnerEdad){
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
                    when(opcionSpinnerClase){
                        "Brujo"->{
                            when(opcionSpinnerEdad){
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
                            when(opcionSpinnerEdad){
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
                            when(opcionSpinnerEdad){
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

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}