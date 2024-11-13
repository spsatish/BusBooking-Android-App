package com.example.newbusbook

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Project.db", null, 1) {

    companion object {
        const val DBNAME = "Project.db"
    }

    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT, email TEXT, fullname TEXT)")
        MyDB.execSQL("CREATE TABLE buses(id TEXT PRIMARY KEY, departure TEXT, arrival TEXT, date TEXT, total_seats TEXT)")
        MyDB.execSQL("CREATE TABLE admin(username TEXT PRIMARY KEY, password TEXT, email TEXT, fullname TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        MyDB.execSQL("DROP TABLE IF EXISTS users")
        MyDB.execSQL("DROP TABLE IF EXISTS buses")
        MyDB.execSQL("DROP TABLE IF EXISTS admin")
    }

    fun insertData(fullname: String, email: String, username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("fullname", fullname)
            put("email", email)
            put("username", username)
            put("password", password)
        }
        val result = MyDB.insert("users", null, contentValues)
        return result != -1L
    }

    fun insertAdmin(fullname: String, email: String, username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("fullname", fullname)
            put("email", email)
            put("username", username)
            put("password", password)
        }
        val result = MyDB.insert("admin", null, contentValues)
        return result != -1L
    }

    fun checkUsername(username: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ?", arrayOf(username))
        return cursor.count > 0
    }

    fun checkAdminUsername(username: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM admin WHERE username = ?", arrayOf(username))
        return cursor.count > 0
    }

    fun checkUsernamePassword(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", arrayOf(username, password))
        return cursor.count > 0
    }

    fun checkAdminUsernamePassword(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM admin WHERE username = ? AND password = ?", arrayOf(username, password))
        return cursor.count > 0
    }

    fun insertBus(id: String, departure: String, arrival: String, date: String, totalSeats: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("id", id)
            put("departure", departure)
            put("arrival", arrival)
            put("date", date)
            put("total_seats", totalSeats)
        }
        val result = MyDB.insert("buses", null, contentValues)
        return result != -1L
    }

    fun updateBus(id: String, departure: String, arrival: String, date: String, totalSeats: String): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("departure", departure)
            put("arrival", arrival)
            put("date", date)
            put("total_seats", totalSeats)
        }
        val cursor = MyDB.rawQuery("SELECT * FROM buses WHERE id = ?", arrayOf(id))
        return if (cursor.count > 0) {
            val result = MyDB.update("buses", contentValues, "id=?", arrayOf(id))
            result != -1
        } else {
            false
        }
    }

    fun deleteBus(id: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM buses WHERE id = ?", arrayOf(id))
        return if (cursor.count > 0) {
            val result = MyDB.delete("buses", "id=?", arrayOf(id))
            result != -1
        } else {
            false
        }
    }

    fun viewBuses(): Cursor {
        val MyDB = this.writableDatabase
        return MyDB.rawQuery("SELECT * FROM buses", null)
    }

    fun checkId(id: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM buses WHERE id = ?", arrayOf(id))
        return cursor.count > 0
    }
}
