package com.example.personajecreacion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.logging.Logger

class MonstruoDataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {

    val log = Logger.getLogger("DataBaseHelper")

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE = "MONSTRUO.db"
        private const val TABLA_MONSTRUO = "Monstruo"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_NIVEL = "nivel"
        private const val COLUMN_SALUD = "salud"
        private const val COLUMN_ATAQUE = "ataque"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        log.info("on create")
        val createTableMonstruo = "CREATE TABLE TABLA_MONSTRUO(" +
                "$COLUMN_NOMBRE TEXT PRIMARY KEY," +
                "$COLUMN_NIVEL  INTEGER," +
                "$COLUMN_SALUD INTEGER," +
                "$COLUMN_ATAQUE INTEGER)"



        if (db != null) {

            db.execSQL(createTableMonstruo)
            val insertInto =
                "INSERT INTO $TABLA_MONSTRUO ($COLUMN_NOMBRE, $COLUMN_NIVEL, $COLUMN_SALUD, $COLUMN_ATAQUE) " +
                        "VALUES('IT', 1, 100, 5)," +
                        "('IT2', 2, 125, 10)," +
                        "('IT3', 3, 150, 15)"
            db.execSQL(insertInto);

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS ${TABLA_MONSTRUO}")
            onCreate(db)
        }
    }

    fun getPersonaje(nombre: String?): Monstruo? {
        var monstruo: Monstruo? = null
        val selectQuery = "SELECT * FROM $TABLA_MONSTRUO WHERE $COLUMN_NOMBRE = '$nombre'"
        val dbRead = this.readableDatabase
        val cursor = dbRead.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
            val nivel = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NIVEL))
            val salud = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SALUD))
            val ataque = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ATAQUE ))


            monstruo = Monstruo(nombre, nivel)
            monstruo.setSalud(salud)
            monstruo.setAtaque(ataque)
        } else {
            cursor.close()
            dbRead.close()
        }

        log.info("personaje sacado de bbdd $monstruo")
        return monstruo
    }
}