package com.example.teste1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


data class Usuario(
    val nome: String,
    val email: String,
    val ddd: String,
    val telefone: String,
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val uf: String
)

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "usuario.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "Usuario"
        const val COLUMN_ID = "id"
        const val COLUMN_NOME = "nome"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_DDD = "ddd"
        const val COLUMN_TELEFONE = "telefone"
        const val COLUMN_CEP = "cep"
        const val COLUMN_LOGRADOURO = "logradouro"
        const val COLUMN_BAIRRO = "bairro"
        const val COLUMN_LOCALIDADE = "localidade"
        const val COLUMN_UF = "uf"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOME TEXT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_DDD TEXT, " +
                "$COLUMN_TELEFONE TEXT, " +
                "$COLUMN_CEP TEXT, " +
                "$COLUMN_LOGRADOURO TEXT, " +
                "$COLUMN_BAIRRO TEXT, " +
                "$COLUMN_LOCALIDADE TEXT, " +
                "$COLUMN_UF TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUsuario(usuario: Usuario) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME, usuario.nome)
            put(COLUMN_EMAIL, usuario.email)
            put(COLUMN_DDD, usuario.ddd)
            put(COLUMN_TELEFONE, usuario.telefone)
            put(COLUMN_CEP, usuario.cep)
            put(COLUMN_LOGRADOURO, usuario.logradouro)
            put(COLUMN_BAIRRO, usuario.bairro)
            put(COLUMN_LOCALIDADE, usuario.localidade)
            put(COLUMN_UF, usuario.uf)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getUsuario(nome: String): Usuario? {
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NOME = ?"
        val db = this.readableDatabase
        var usuario: Usuario? = null
        val cursor = db.rawQuery(query, arrayOf(nome))

        if (cursor.moveToFirst()) {
            val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            val ddd = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DDD))
            val telefone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE))
            val cep = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CEP))
            val logradouro = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGRADOURO))
            val bairro = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BAIRRO))
            val localidade = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCALIDADE))
            val uf = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UF))
            usuario = Usuario(nome, email, ddd, telefone, cep, logradouro, bairro, localidade, uf)
        }

        cursor.close()
        db.close()
        return usuario
    }
}