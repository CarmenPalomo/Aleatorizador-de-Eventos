package com.example.personajecreacion

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.logging.Logger

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    val log = Logger.getLogger("DataBaseHelper")

    companion object {
        // nombre, tipo, peso, url, unidades
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "OBJETOS_ALEATORIOS.db"
        private const val TABLA_OBJETOS = "Objeto"
        private const val KEY_ID = "_id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_TIPO = "tipo"
        private const val COLUMN_PESO = "peso"
        private const val COLUMN_URL = "url"
        private const val COLUMN_UNIDADES = "unidades"
        private const val COLUMN_PRECIO = "precio"
    }


    override fun onCreate(db: SQLiteDatabase?) {

        log.info("on create")
        val createTable = "CREATE TABLE $TABLA_OBJETOS(" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_TIPO TEXT, " +
                "$COLUMN_PESO INTEGER, " +
                "$COLUMN_URL TEXT, " +
                "$COLUMN_UNIDADES INTEGER," +
                "$COLUMN_PRECIO)"
        if (db != null) {
            log.info("creating table $createTable")
            db.execSQL(createTable)
            val insertInto =
                "INSERT INTO $TABLA_OBJETOS($COLUMN_NOMBRE, $COLUMN_TIPO, $COLUMN_PESO, $COLUMN_URL, $COLUMN_UNIDADES ) " +
                        "VALUES('YELMO', 'PROTECCION', 2, 'yelmo.jpg', 1)," +
                        "('POCION', 'PROTECCION', 1, 'pocion.jpg', 1)," +
                        "('MARTILLO', 'ARMA', 3, 'martillo.jpg', 1)," +
                        "('GARRAS', 'ARMA', 2, 'garras.jpg', 1)," +
                        "('ESPADA', 'ARMA', 3, 'espada.jpg', 1)," +
                        "('ESCUDO', 'PROTECCION', 1, 'escudo.jpg', 1)," +
                        "('DAGA', 'ARMA', 1, 'daga.jpg', 1)," +
                        "('COMIDA', 'OBJETO', 2, 'comida.jpg', 1)," +
                        "('COFRE', 'OBJETO', 3, 'cofre.jpg', 1)," +
                        "('CASCO', 'PROTECCION', 2, 'casco.jpg', 1)"
            db.execSQL(insertInto);
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            log.info("dropping table $TABLA_OBJETOS")
            db.execSQL("DROP TABLE IF EXISTS $TABLA_OBJETOS")
            onCreate(db)
        }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun insertarObjeto(objeto: Articulo) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, objeto.getNombre())
            put(COLUMN_TIPO, objeto.getTipo()?.name)
            put(COLUMN_PESO, objeto.getPeso())
            put(COLUMN_URL, objeto.getUrl())
            put(COLUMN_UNIDADES, objeto.getUnidades())
        }
        db?.insert(TABLA_OBJETOS, null, values)
        db.close()
    }

    fun getArticuloAleatorio(): Articulo {
        val objeto = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_OBJETOS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                val peso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PESO))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                val unidades = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UNIDADES))
                val precio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRECIO))
                objeto.add(Articulo(nombre, peso, getTipoArt(tipo), url, unidades, precio))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        val num = (0..objeto.size - 1).random()
        return objeto[num]
    }

    fun getTipoArt(tipo: String): Articulo.TipoArt? {
        return when (tipo) {
            Articulo.TipoArt.ARMA.name -> Articulo.TipoArt.ARMA
            Articulo.TipoArt.OBJETO.name -> Articulo.TipoArt.OBJETO
            Articulo.TipoArt.ORO.name -> Articulo.TipoArt.ORO
            Articulo.TipoArt.PROTECCION.name -> Articulo.TipoArt.PROTECCION
            else -> null
        }
    }

}