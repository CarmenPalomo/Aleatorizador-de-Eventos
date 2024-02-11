package com.example.personajecreacion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.logging.Logger

class MercaderDataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {
    val log = Logger.getLogger("DataBaseHelper")

    companion object {
        // nombre, tipo, peso, url, unidades
        private const val DATABASE_VERSION = 6
        private const val DATABASE = "OBJETOS_MERCADER.db"
        private const val TABLA_MERCADER = "Mercader"
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
        val createTable = "CREATE TABLE $TABLA_MERCADER(" +
                "$COLUMN_NOMBRE TEXT PRIMARY KEY, " +
                "$COLUMN_TIPO TEXT, " +
                "$COLUMN_PESO INTEGER, " +
                "$COLUMN_URL TEXT, " +
                "$COLUMN_UNIDADES INTEGER," +
                "$COLUMN_PRECIO INTEGER)"
        if (db != null) {
            log.info("creating table $createTable")
            db.execSQL(createTable)
            val insertInto =
                "INSERT INTO ${TABLA_MERCADER}(${COLUMN_NOMBRE}, ${COLUMN_TIPO}, ${COLUMN_PESO}, ${COLUMN_URL}, ${COLUMN_UNIDADES}, ${COLUMN_PRECIO}) " +
                        "VALUES('YELMO', 'PROTECCION', 2, 'yelmo', 1, 23)," +
                        "('POCION', 'PROTECCION', 1, 'pocion', 10, 5)," +
                        "('MARTILLO', 'ARMA', 3, 'martillo', 4, 18)," +
                        "('GARRAS', 'ARMA', 2, 'garras', 3, 10)," +
                        "('ESPADA', 'ARMA', 3, 'espada', 1, 20)," +
                        "('ESCUDO', 'PROTECCION', 1, 'escudo', 1, 18)," +
                        "('DAGA', 'ARMA', 1, 'daga', 7, 15)," +
                        "('COMIDA', 'OBJETO', 2, 'comida', 20, 10)," +
                        "('COFRE', 'OBJETO', 3, 'cofre', 1, 30)," +
                        "('CASCO', 'PROTECCION', 2, 'casco', 1, 22)"
            db.execSQL(insertInto);
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            log.info("dropping table $TABLA_MERCADER")
            db.execSQL("DROP TABLE IF EXISTS $TABLA_MERCADER")
            onCreate(db)
        }
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        log.info("dropping table $TABLA_MERCADER")
        db?.execSQL("DROP TABLE IF EXISTS $TABLA_MERCADER")
        onUpgrade(db, oldVersion, newVersion)
    }

    fun getArticuloAleatorio(): Articulo {
        val objeto = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_MERCADER"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            log.info("obtenemos articulos")
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
        val num = (0..<objeto.size).random()
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


    fun getArticulos(): ArrayList<Articulo> {
        val articulos = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_MERCADER"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                val peso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PESO))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                val unidades = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UNIDADES))
                articulos.add(Articulo(nombre, peso, getTipoArt(tipo), url, unidades, 0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return articulos
    }

    fun actualizarUnidades(articulo: Articulo) {
        val db = this.writableDatabase
        val selectQuery = "UPDATE $TABLA_MERCADER " +
                "SET $COLUMN_UNIDADES = ${articulo.getUnidades()}, $COLUMN_PRECIO = ${articulo.getPrecio()} " +
                "WHERE $COLUMN_NOMBRE = '${articulo.getNombre()}'"
        db.execSQL(selectQuery);
        db.close()
    }

    fun sumarUnidades(articulos: ArrayList<Articulo>) {
        for (articulo in articulos) {
            val dbRead = this.readableDatabase
            val selectSql =
                "SELECT * FROM $TABLA_MERCADER WHERE $COLUMN_NOMBRE='${articulo.getNombre()}'"
            val cursor = dbRead.rawQuery(selectSql, null)
            if (cursor.moveToFirst()) {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO))
                val peso = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PESO))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                val unidades = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_UNIDADES))
                val precio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRECIO))
                val articuloEncontrado =
                    Articulo(nombre, peso, getTipoArt(tipo), url, unidades, precio)
                articuloEncontrado.sumaUnidades(articulo.getUnidades())
                this.actualizarUnidades(articuloEncontrado)
            }
            dbRead.close()
        }
    }
}
