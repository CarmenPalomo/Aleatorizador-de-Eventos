package com.example.personajecreacion

import android.content.ContentValues
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
        private const val DATABASE_VERSION = 3
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
        private const val COLUMN_NOMBRE_ARTICULO = "nombreArticulo"
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
                "$COLUMN_NOMBRE_ARTICULO TEXT," +
                "$COLUMN_PESO INTEGER," +
                "$COLUMN_TIPO TEXT," +
                "$COLUMN_IMAGEN TEXT," +
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
            db.execSQL("DROP TABLE IF EXISTS $TABLA_MOCHILA")
            db.execSQL("DROP TABLE IF EXISTS $TABLA_ARTICULO")
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
                    "VALUES ('${personaje.getIdPersonaje()}','${personaje.getNombre()}','${personaje.getEstadoVital()}'," +
                    "'${personaje.getRaza()}','${personaje.getClase()}', ${personaje.getExperiencia()},${personaje.getNivel()}," +
                    "${personaje.getSalud()}, ${personaje.getAtaque()}, ${personaje.getDefensa()} )"
        db.execSQL(insertInto);
        val mochilaId = db.insert(TABLA_MOCHILA, null,
            ContentValues().apply {
                put(COLUMN_ESPACIO_MOCHILA, personaje.getMochila()!!.getEspacio())
                put(ID_USUARIO, personaje.getIdPersonaje())
            })
        personaje.getMochila()!!.setIdMochila(mochilaId)
    }


    fun personajeRegistrado(id: Personaje): Boolean {
        val db = this.readableDatabase
        val selectQuery = "SELECT $ID_USUARIO FROM ${TABLA_PERSONAJE} WHERE $ID_USUARIO = $id"
        val cursor = db.rawQuery(selectQuery, null)
        return cursor.count >= 1
    }

    fun getPersonaje(idPersonaje: String?): Personaje? {
        var personaje: Personaje? = null
        val selectQuery = "SELECT * FROM $TABLA_PERSONAJE WHERE ${ID_USUARIO} = '${idPersonaje}'"
        val dbRead = this.readableDatabase
        val cursor = dbRead.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
            val estadoVital = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ESTADOVITAL))
            val raza = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RAZA))
            val clase = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLASE))
            val experiencia = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EXPERIENCIA))
            val nivel = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NIVEL))
            val salud = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SALUD))
            val ataque = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ATAQUE))
            val defensa = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DEFENSA))
            cursor.close()
            dbRead.close()
            val mochila = obtenerMochila(idPersonaje)
            personaje = Personaje(idPersonaje, nombre, estadoVital, raza, clase, mochila)
            personaje.setExperiencia(experiencia)
            personaje.setNivel(nivel)
            personaje.setSalud(salud)
            personaje.setAtaque(ataque)
            personaje.setDefensa(defensa)
        } else {
            cursor.close()
            dbRead.close()
        }

        return personaje
    }

    private fun obtenerMochila(idPersonaje: String?): Mochila? {
        val dbRead = this.readableDatabase
        var mochilaResultado: Mochila? = null
        val selectQuery = "SELECT * FROM $TABLA_MOCHILA WHERE ${ID_USUARIO} = '${idPersonaje}'"
        val cursor = dbRead.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            val espacio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ESPACIO_MOCHILA))
            val idMochila = cursor.getLong(cursor.getColumnIndexOrThrow(ID_MOCHILA))
            cursor.close()
            dbRead.close()
            val articulos = obtenerArticulos(idMochila)
            mochilaResultado = Mochila(espacio, articulos)
            mochilaResultado.setIdMochila(idMochila)
        } else {
            cursor.close()
            dbRead.close()
        }
        return mochilaResultado
    }

    private fun obtenerArticulos(idMochila: Long): ArrayList<Articulo> {
        val dbRead = this.readableDatabase
        val listaArticulos = arrayListOf<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_ARTICULO WHERE ${ID_MOCHILA} = '${idMochila}'"
        val cursor = dbRead.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val idArticulo = cursor.getLong(cursor.getColumnIndexOrThrow(ID_ARTICULO))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_ARTICULO))
                val peso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PESO))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                val imagen = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN))
                val unidades = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UNIDADES))
                val precio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRECIO))
                val articuloNuevo = Articulo(nombre, peso, Articulo.TipoArt.valueOf(tipo), imagen, unidades, precio)
                articuloNuevo.setIdArticulo(idArticulo)
                listaArticulos.add(articuloNuevo)
            } while (cursor.moveToNext())
        }
        cursor.close()
        dbRead.close()
        return listaArticulos
    }


}