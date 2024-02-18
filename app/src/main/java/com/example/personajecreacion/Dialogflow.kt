package com.example.personajecreacion


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.KeyEvent
import android.view.View
//import kotlinx.android.synthetic.Dialogflow.activity_dialogflow.*
import android.view.LayoutInflater
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.dialogflow.v2.*
import java.util.*
import android.widget.*
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.text.Editable
import com.google.cloud.dialogflow.v2.DetectIntentResponse
import com.google.cloud.dialogflow.v2.SessionName
import com.google.cloud.dialogflow.v2.SessionsClient
import com.google.cloud.dialogflow.v2.SessionsSettings
import com.google.common.collect.Lists
import android.app.Activity;
import android.os.AsyncTask;
import com.google.cloud.dialogflow.v2.*





class Dialogflow : AppCompatActivity() {

    val USUARIO = 0
    val BOT = 1

    private lateinit var enviar: ImageView

    private lateinit var atras: ImageView
    private lateinit var cajadetexto : EditText

    private var cliente: SessionsClient? = null
    private var sesion: SessionName? = null
    private lateinit var uuid: String
    private var asistente_voz: TextToSpeech? = null
    private lateinit var linear_chat : LinearLayout
    private lateinit var personaje: Personaje

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialogflow)

        enviar = findViewById(R.id.enviar)
        atras =  findViewById(R.id.boton_atras1)
        cajadetexto  = findViewById(R.id.cajadetexto)
        uuid =  UUID.randomUUID().toString()
        personaje = intent.getParcelableExtra("Personaje")!!

        linear_chat = findViewById(R.id.linear_chat)

        val scrollview = findViewById<ScrollView>(R.id.scroll_chat)
        scrollview.post {
            scrollview.fullScroll(ScrollView.FOCUS_DOWN)
        }

        val cajaMensajes = findViewById<EditText>(R.id.cajadetexto)
        cajaMensajes.setOnKeyListener { view, keyCode, event ->
            if (event.action === KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                        enviarMensaje(enviar)
                        true
                    }
                    else -> {
                    }
                }
            }
            false
        }

        atras.setOnClickListener{
            var intent = Intent(this,AventuraActivity::class.java)
            intent.putExtra("Personaje",personaje)
            startActivity(intent)
        }
        enviar.setOnClickListener(this::enviarMensaje)

        iniciarAsistente()

        iniciarAsistenteVoz()

    }

    private fun iniciarAsistente() {
        try {

            val config = resources.openRawResource(R.raw.credenciales)

            val credenciales = GoogleCredentials.fromStream(config)
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"))

            val projectId = (credenciales as ServiceAccountCredentials).projectId

            val generarConfiguracion: SessionsSettings.Builder = SessionsSettings.newBuilder()

            val configurarSesiones: SessionsSettings =
                generarConfiguracion.setCredentialsProvider(FixedCredentialsProvider.create(credenciales))
                    .build()
            cliente = SessionsClient.create(configurarSesiones)
            sesion = SessionName.of(projectId, uuid)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun iniciarAsistenteVoz() {

        asistente_voz = TextToSpeech(applicationContext,object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (status != TextToSpeech.ERROR){
                    asistente_voz?.language=Locale("es")
                }
            }

        })

    }

    private fun enviarMensaje(view: View) {

        var mensaje = cajadetexto.text.toString()

        if (mensaje.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this@Dialogflow, getString(R.string.placeholder), Toast.LENGTH_LONG).show()
        }

        else {
            agregarTexto(mensaje, USUARIO)
            cajadetexto.setText("")

            if (mensaje == personaje.getNombre()){
                mensaje = "Tenemos el mismo nombre"
            }else{
                if (mensaje == mensaje.toUpperCase()&&mensaje.contains("?")){
                    mensaje = "pregunta fea"
                }else{
                    if (mensaje == mensaje.toUpperCase()){
                        mensaje = "Enfado"
                    }
                }
            }



            val ingresarConsulta =
                QueryInput.newBuilder().setText(TextInput.newBuilder().setText(mensaje).setLanguageCode("es")).build()
            solicitarTarea(this@Dialogflow, sesion!!, cliente!!, ingresarConsulta).execute()
        }
    }

    private fun agregarTexto(mensaje: String, type: Int) {

        val layoutFrm: FrameLayout
        when (type) {
            USUARIO -> layoutFrm = agregarTextoUsuario()
            BOT -> layoutFrm = agregarTextoBot()
            else -> layoutFrm = agregarTextoBot()
        }

        layoutFrm.isFocusableInTouchMode = true

        linear_chat.addView(layoutFrm)

        val textview = layoutFrm.findViewById<TextView>(R.id.msg_chat)
        textview.setText(mensaje)


        layoutFrm.requestFocus()

        cajadetexto.requestFocus()

        if(type!= USUARIO) asistente_voz?.speak(mensaje,TextToSpeech.QUEUE_FLUSH,null)

    }

    fun agregarTextoUsuario(): FrameLayout {
        val inflater = LayoutInflater.from(this@Dialogflow)
        return inflater.inflate(R.layout.mensaje_usuario, null) as FrameLayout
    }

    fun agregarTextoBot(): FrameLayout {
        val inflater = LayoutInflater.from(this@Dialogflow)
        return inflater.inflate(R.layout.mensaje_bot, null) as FrameLayout
    }

    fun validar(response: DetectIntentResponse?) {
        try {
            if (response != null) {

                var respuestaBot: String = ""

                if(response.queryResult.fulfillmentText==" ")
                    respuestaBot = response.queryResult.fulfillmentMessagesList[0].text.textList[0].toString()
                else
                    respuestaBot = response.queryResult.fulfillmentText

                // Pasamos el método agregarTexto()
                agregarTexto(respuestaBot, BOT)

            }
        }catch (e:Exception){
            agregarTexto(getString(R.string.ingresa_mensaje), BOT)
        }
    }
    override fun onDestroy() {
        super.onDestroy()

        if(asistente_voz !=null){
            asistente_voz?.stop()
            asistente_voz?.shutdown()
        }
    }


    class solicitarTarea : AsyncTask<Void, Void, DetectIntentResponse> {

        var actividad: Activity? = null
        private var sesion: SessionName? = null
        private var sesionesCliente: SessionsClient? = null
        private var entradaConsulta: QueryInput? = null

        constructor(actividad: Activity, sesion: SessionName, sesionesCliente: SessionsClient, entradaConsulta: com.google.cloud.dialogflow.v2.QueryInput){
            this.actividad = actividad
            this.sesion = sesion
            this.sesionesCliente = sesionesCliente
            this.entradaConsulta = entradaConsulta
        }

        override fun doInBackground(vararg params: Void?): DetectIntentResponse? {

            try {
                val detectarIntentosolicitarTarea = DetectIntentRequest.newBuilder()
                    .setSession(sesion.toString())
                    .setQueryInput(entradaConsulta)
                    .build()
                return sesionesCliente?.detectIntent(detectarIntentosolicitarTarea)
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
        override fun onPostExecute(result: DetectIntentResponse?) {
            (actividad as Dialogflow).validar(result)
        }


    }

}

