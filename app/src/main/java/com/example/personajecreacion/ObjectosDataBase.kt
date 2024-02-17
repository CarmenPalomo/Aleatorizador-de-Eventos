package com.example.personajecreacion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.logging.Logger

class ObjectosDataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    val log = Logger.getLogger("DataBaseHelper")

    companion object {
        // nombre, tipo, peso, url, unidades
        private const val DATABASE_VERSION = 6
        private const val DATABASE = "OBJETOS_ALEATORIOS.db"
        private const val TABLA_OBJETOS = "Objeto"
        private const val TABLA_OBJETOS_MONSTRUO = "Monstruo"
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
        val createTableObjetosMonstruo = "CREATE TABLE $TABLA_OBJETOS_MONSTRUO(" +
                "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_TIPO TEXT, " +
                "$COLUMN_PESO INTEGER, " +
                "$COLUMN_URL TEXT, " +
                "$COLUMN_UNIDADES INTEGER," +
                "$COLUMN_PRECIO INTEGER)"
        if (db != null) {
            log.info("creating table $createTableObjetosMonstruo")
            db.execSQL(createTableObjetosMonstruo)
            val insertInto =
                "INSERT INTO $TABLA_OBJETOS_MONSTRUO($COLUMN_NOMBRE, $COLUMN_TIPO, $COLUMN_PESO, $COLUMN_URL, $COLUMN_UNIDADES, $COLUMN_PRECIO) " +
                        "VALUES('GARRAS_MONSTRUO', 'OBJETOMONSTRUO', 2, 'garras_monstruo', 1, 3)," +
                        "('PELO', 'OBJETOMONSTRUO', 1, 'pelo', 1, 3)," +
                        "('BRONCE', 'OBJETOMONSTRUO', 3, 'bronce', 1, 3)," +
                        "('ORO', 'OBJETOMONSTRUO', 2, 'oro', 1, 3)," +
                        "('HIERRO', 'OBJETOMONSTRUO', 3, 'hierro', 1, 3)"
            db.execSQL(insertInto);
        }

        val createTable = "CREATE TABLE $TABLA_OBJETOS(" +
                "$KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_TIPO TEXT, " +
                "$COLUMN_PESO INTEGER, " +
                "$COLUMN_URL TEXT, " +
                "$COLUMN_UNIDADES INTEGER," +
                "$COLUMN_PRECIO INTEGER)"
        if (db != null) {
            log.info("creating table $createTable")
            db.execSQL(createTable)
            val insertInto =
                "INSERT INTO $TABLA_OBJETOS($COLUMN_NOMBRE, $COLUMN_TIPO, $COLUMN_PESO, $COLUMN_URL, $COLUMN_UNIDADES, $COLUMN_PRECIO) " +
                        "VALUES('YELMO', 'PROTECCION', 2, 'yelmo', 1, 3)," +
                        "('POCION', 'PROTECCION', 1, 'pocion', 1, 3)," +
                        "('MARTILLO', 'ARMA', 3, 'martillo', 1, 3)," +
                        "('GARRAS', 'ARMA', 2, 'garras', 1, 3)," +
                        "('ESPADA', 'ARMA', 3, 'espada', 1, 3)," +
                        "('ESCUDO', 'PROTECCION', 1, 'escudo', 1, 3)," +
                        "('DAGA', 'ARMA', 1, 'daga', 1, 3)," +
                        "('COMIDA', 'OBJETO', 2, 'comida', 1, 3)," +
                        "('COFRE', 'OBJETO', 3, 'cofre', 1, 3)," +
                        "('CASCO', 'PROTECCION', 2, 'casco', 1, 3)"
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
        log.info("dropping table $TABLA_OBJETOS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLA_OBJETOS")
        onUpgrade(db, oldVersion, newVersion)
    }



    fun getArticuloAleatorio(): Articulo {
        val objeto = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_OBJETOS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            log.info("obtenemos articulos")
            do {
                val idArticulo  = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                val peso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PESO))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                val unidades = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UNIDADES))
                val precio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRECIO))
                val element = Articulo(nombre, peso, Articulo.TipoArt.valueOf(tipo), url, unidades, precio)
                element.setIdArticulo(idArticulo)
                objeto.add(element)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        val num = (0..<objeto.size).random()
        return objeto[num]
    }

    fun getArticuloAleatorioMonstruo(): Articulo {
        val objeto = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_OBJETOS_MONSTRUO"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            log.info("obtenemos articulos")
            do {
                val idArticulo  = cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                val peso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PESO))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                val unidades = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UNIDADES))
                val precio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRECIO))
                val element = Articulo(nombre, peso, Articulo.TipoArt.valueOf(tipo), url, unidades, precio)
                element.setIdArticulo(idArticulo)
                objeto.add(element)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        val num = (0..<objeto.size).random()
        return objeto[num]
    }
}