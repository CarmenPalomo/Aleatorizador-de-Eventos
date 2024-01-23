package com.example.personajecreacion

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context, DATABASE: String?, DATABASE_VERSION: Int): SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {

    companion object{
        // nombre, tipo, peso, url, unidades
        private const val DATABASE_VERSION = 1
        private const val  DATABASE = "OBJETOS_ALEATORIOS.db"
        private const val TABLA_OBJETOS = "Objeto"
        private const val KEY_ID = "_id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_TIPO = "tipo"
        private const val COLUMN_PESO = "peso"
        private const val COLUMN_URL = "url"
        private const val COLUMN_UNIDADES = "unidades"


    }



    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLA_OBJETOS($KEY_ID INTEGER PRIMARY KEY," +
                "$COLUMN_NOMBRE TEXT, $COLUMN_TIPO TEXT, $COLUMN_PESO INTEGER, $COLUMN_URL TEXT, " +
                "$COLUMN_UNIDADES INTEGER)"
        if (db != null) {
            db.execSQL(createTable)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLA_OBJETOS")
        }

        onCreate(db)
    }

    fun insertarObjeto(objeto: Articulo){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, objeto.getNombre())
            put(COLUMN_TIPO, objeto.getTipo())
            put(COLUMN_PESO, objeto.getPeso())
            put(COLUMN_URL, objeto.getUrl())
            put(COLUMN_UNIDADES, objeto.getUnidades())
        }
        db.insert(TABLA_OBJETOS, null, values)
        db.close()

    }

    @SuppressLint("Range")
    fun getObjeto(): ArrayList<Articulo>{
        val objeto = ArrayList<Articulo>()
        val selectQuery = "SELECT * FROM $TABLA_OBJETOS"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO))
                val peso = cursor.getInt(cursor.getColumnIndex(COLUMN_PESO))
                val url = cursor.getString(cursor.getColumnIndex(COLUMN_URL))
                val unidades = cursor.getInt(cursor.getColumnIndex(COLUMN_UNIDADES))

            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return objeto
    }

}