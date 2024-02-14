package com.example.personajecreacion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.logging.Logger


class PersonajeDataBase(context: Context) :
    SQLiteOpenHelper(
        context,
        PersonajeDataBase.DATABASE,
        null,
        PersonajeDataBase.DATABASE_VERSION
    ) {
    val log = Logger.getLogger("DataBaseHelper")

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "Personaje.db"
        private const val TABLA_PERSONAJE = "Personaje"
        private const val ID_USUARIO = "idUsuario"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_ESTADOVITAL = "estadoVital"
        private const val COLUMN_RAZA = "raza"
        private const val COLUMN_CLASE = "clase"
        private const val COLUMN_EXPERIENCIA = "experiencia"
        private const val COLUMN_NIVEL = "NIVEL"
        private const val COLUMN_SALUD = "salud"
        private const val COLUMN_ATAQUE = "ataque"
        private const val COLUMN_DEFENSA = "defensa"


        private const val TABLA_MOCHILA = "Mochila"
        private const val ID_MOCHILA = "idMochila"
        private const val COLUMN_ESPACIO_MOCHILA = "pesoMochila"


        private const val TABLA_ARTICULO = "Articulo"
        private const val ID_ARTICULO = "idArticulo"
        private const val COLUMN_PESO = "peso"
        private const val COLUMN_TIPO = "tipo"
        private const val COLUMN_IMAGEN = "imagen"
        private const val COLUMN_UNIDADES = "unidades"
        private const val COLUMN_PRECIO = "precio"
    }


    override fun onCreate(db: SQLiteDatabase?) {

        log.info("on create")
        val createTablePersonaje = "CREATE TABLE $TABLA_PERSONAJE(" +
                "$ID_USUARIO TEXT PRIMARY KEY," +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_ESTADOVITAL INTEGER, " +
                "$COLUMN_RAZA TEXT, " +
                "$COLUMN_CLASE INTEGER," +
                "$COLUMN_EXPERIENCIA INTEGER," +
                "$COLUMN_NIVEL TEXT, " +
                "$COLUMN_SALUD INTEGER, " +
                "$COLUMN_ATAQUE TEXT, " +
                "$COLUMN_DEFENSA INTEGER)"

        val createTableMochila = "CREATE TABLE $TABLA_MOCHILA(" +
                "$ID_MOCHILA INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_ESPACIO_MOCHILA INTEGER," +
                "$ID_USUARIO INTEGER," +
                "FOREIGN KEY ($ID_USUARIO) REFERENCES $TABLA_PERSONAJE($ID_USUARIO))"

        val createTableArticulo = "CREATE TABLE $TABLA_ARTICULO(" +
                "$ID_ARTICULO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_PESO INTEGER," +
                "$COLUMN_TIPO INTEGER," +
                "$COLUMN_IMAGEN INTEGER," +
                "$COLUMN_UNIDADES INTEGER," +
                "$COLUMN_PRECIO INTEGER," +
                "$ID_MOCHILA INTEGER," +
                "FOREIGN KEY ($ID_MOCHILA) REFERENCES $TABLA_MOCHILA($ID_MOCHILA))"


        if (db != null) {
            db.execSQL(createTablePersonaje)
            db.execSQL(createTableMochila)
            db.execSQL(createTableArticulo)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            log.info("dropping table $TABLA_PERSONAJE")
            db.execSQL("DROP TABLE IF EXISTS $TABLA_PERSONAJE")
            onCreate(db)
        }
    }

    fun insertarPersonaje(personaje: Personaje) {
        val db = this.writableDatabase
        val insertInto =
            "INSERT INTO ${TABLA_PERSONAJE}(${ID_USUARIO}, ${COLUMN_NOMBRE}, " +
                    "${COLUMN_ESTADOVITAL}, ${COLUMN_RAZA}, ${COLUMN_CLASE} , " +
                    "${COLUMN_EXPERIENCIA}, ${COLUMN_NIVEL} , ${COLUMN_SALUD}," +
                    "${COLUMN_ATAQUE}, ${COLUMN_DEFENSA})" +
                    "VALUES (${personaje.getIdPersonaje()},${personaje.getNombre()},${personaje.getEstadoVital()}," +
                    "${personaje.getRaza()},${personaje.getClase()}, ${personaje.getExperiencia()},${personaje.getNivel()}," +
                    "${personaje.getSalud()}, ${personaje.getAtaque()}, ${personaje.getDefensa()} )"

        db.execSQL(insertInto);
    }


}