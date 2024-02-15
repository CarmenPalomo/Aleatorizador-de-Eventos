package com.example.personajecreacion

import android.os.Parcel
import android.os.Parcelable

data class Personaje(
    private var idPersonaje: String?,
    private var nombre: String?,
    private var estadoVital: String?,
    private var raza: String?,
    private var clase: String?,
    private var mochila: Mochila?
) : Parcelable {

    private var experiencia: Int
    private var nivel: Int
    private var salud: Int
    private var ataque: Int
    private var defensa: Int
    private var suerte : Int

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Mochila::class.java.classLoader),
    ) {
        experiencia = parcel.readInt()
        nivel = parcel.readInt()
        salud = parcel.readInt()
        ataque = parcel.readInt()
        defensa = parcel.readInt()
    }

    init {
        salud = 100
        ataque = 10
        experiencia = 0
        nivel = 1
        defensa = 4
        suerte = (0..10).random()
    }


    fun getIdPersonaje(): String? {
        return idPersonaje
    }
    fun getExperiencia(): Int? {
        return experiencia
    }
    fun getSalud(): Int? {
        return salud
    }

    fun getAtaque(): Int? {
        return ataque
    }

    fun getNivel(): Int? {
        return nivel
    }

    fun getDefensa(): Int? {
        return defensa
    }
    fun getNombre(): String? {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getEstadoVital(): String? {
        return estadoVital
    }

    fun setEstadoVital(estadoVital: String) {
        this.estadoVital = estadoVital
    }

    fun getRaza(): String? {
        return raza
    }

    fun setRaza(raza: String) {
        this.raza = raza
    }

    fun getClase(): String? {
        return clase
    }

    fun setClase(clase: String) {
        this.clase = clase
    }

    fun getMochila(): Mochila? {
        return this.mochila
    }



    fun setNivel(nivel: Int) {
        this.nivel = nivel
    }


    fun setSalud(salud: Int) {
        this.salud = salud
    }
    fun setAtaque(ataque: Int) {
        this.ataque = ataque
    }
    fun setDefensa(defensa: Int) {
        this.defensa = defensa
    }

    fun setExperiencia(experienciaGanada: Int) {
        experiencia += experienciaGanada
        while (experiencia >= 1000) {
            subirNivel()
            experiencia -= 1000 // Reducir la experiencia en 1000 al subir de nivel
        }
    }

    fun subirNivel() {
        if (nivel < 10) { // Limitar el nivel a 10
            nivel++
            calcularSalud() // Calcular el nuevo valor de salud al subir de nivel
            calcularAtaque() // Calcular el nuevo valor de ataque al subir de nivel
            calcularDefensa()
        }
    }

    private fun calcularSalud() {
        salud = when (nivel) {
            1 -> 100
            2 -> 200
            3 -> 300
            4 -> 450
            5 -> 600
            6 -> 800
            7 -> 1000
            8 -> 1250
            9 -> 1500
            10 -> 2000
            else -> 100 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }

    private fun calcularAtaque() {
        ataque = when (nivel) {
            1 -> 10
            2 -> 20
            3 -> 25
            4 -> 30
            5 -> 40
            6 -> 100
            7 -> 200
            8 -> 350
            9 -> 400
            10 -> 450
            else -> 10 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }
    private fun calcularDefensa() {
        defensa = when (nivel) {
            1 -> 4
            2 -> 9
            3 -> 14
            4 -> 19
            5 -> 49
            6 -> 59
            7 -> 119
            8 -> 199
            9 -> 349
            10 -> 399
            else -> 4 // Valor por defecto si el nivel está fuera del rango especificado
        }
    }

    fun pelea(monstruo: Monstruo ) {
        var vidaMonstruo = monstruo.getSalud()
        var expGanada = monstruo.getSalud() // La experiencia ganada es igual a la salud inicial del monstruo
        var vidaPersonaje = salud
        var contador = false
        println("¡Un ${monstruo.getNombre()} se acerca!")



        while (vidaMonstruo > 0 && vidaPersonaje > 0) {
            // Preguntar al usuario si desea activar la habilidad
            println("¿Deseas activar la habilidad del personaje? (Sí/No)")
            val respuesta = readLine()?.toLowerCase()


            val evasion = suerte >= 10
            val ataqueMonstruo = if (evasion) 0 else monstruo.getAtaque()

            // Aplicar la defensa del personaje
            val defensaPersonaje = defensa * suerte / 100
            val danoMonstruo = if (evasion) 0 else ataqueMonstruo - defensaPersonaje

            if (!evasion) {
                vidaPersonaje -= danoMonstruo
            }

            println("${nombre} tiene una suerte de ${suerte}% y una defensa de ${defensaPersonaje}.")
            println("${nombre} ha recibido ${danoMonstruo} de daño. Salud de ${nombre}: ${vidaPersonaje}")

            if (vidaMonstruo > 0) {
                // Personaje ataca al monstruo
                vidaMonstruo -= ataque
                println("${nombre} ataca al ${monstruo.getNombre()}. Salud del ${monstruo.getNombre()}: ${vidaMonstruo}")
                if (vidaMonstruo <= 0) {
                    setExperiencia(expGanada)  // El personaje gana experiencia igual a la salud inicial del monstruo
                    println("${nombre} ha derrotado al ${monstruo.getNombre()} y gana ${expGanada} de experiencia.")
                    break
                }

                // Monstruo ataca al personaje
                vidaPersonaje -= ataqueMonstruo
                println("${monstruo.getNombre()} ataca a ${nombre}. Salud de ${nombre}: ${vidaPersonaje}")
            }
        }
    }


    fun cifrado(mensaje : String, ROT : Int) : String{
        val abecedario : ArrayList<Char> = "abcdefghijklmnñopqrstuvwxyz".toList() as ArrayList<Char>
        var stringInv = ""
        var indice = 0

        for(i in mensaje.lowercase().toList() as ArrayList<Char>){
            if(!i.isLetter() || i.isWhitespace()){
                stringInv += i
            } else{
                indice = abecedario.indexOf(i) + ROT
                if (indice >= abecedario.size) {
                    indice -= abecedario.size
                    stringInv += abecedario[indice]
                } else
                    stringInv += abecedario[indice]
            }
        }
        return stringInv.filter { !it.isWhitespace() && it.isLetter() }
    }







    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idPersonaje)
        parcel.writeString(nombre)
        parcel.writeString(estadoVital)
        parcel.writeString(raza)
        parcel.writeString(clase)
        parcel.writeParcelable(mochila, flags)
        parcel.writeInt(experiencia)
        parcel.writeInt(nivel)
        parcel.writeInt(salud)
        parcel.writeInt(ataque)
        parcel.writeInt(defensa)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Personaje(nombre=$nombre, estadoVital=$estadoVital, raza=$raza, clase=$clase, mochila=$mochila, experiencia=$experiencia, nivel=$nivel, salud=$salud, ataque=$ataque, defensa=$defensa)"
    }

    companion object CREATOR : Parcelable.Creator<Personaje> {
        override fun createFromParcel(parcel: Parcel): Personaje {
            return Personaje(parcel)
        }

        override fun newArray(size: Int): Array<Personaje?> {
            return arrayOfNulls(size)
        }
    }

}