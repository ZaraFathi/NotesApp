package com.example.notesapp.data.dao.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context
):SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    companion object{

        val FALSE_STATE:String = "0"
        private const val DB_NAME ="notes.db"
        private const val DB_VERSION =1

        const val NOTES_TABLE ="Notes"
        const val NOTES_ID ="id"
        const val NOTES_TITLE ="title"
        const val NOTES_DETAIL ="detail"
        const val NOTES_DELLTE_STATE ="delltestate"
        const val NOTES_DATE ="date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
      db?.execSQL("CREATE TABLE IF NOT EXISTS $NOTES_TABLE(" +
              "$NOTES_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
              "$NOTES_DETAIL TEXT," +
              "$NOTES_DELLTE_STATE VARCHAR(1)," +
              "$NOTES_DATE VARCHAR(255))")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}


}