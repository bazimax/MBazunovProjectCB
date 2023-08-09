package com.example.mbazunovprojectcb.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MainDbHelper(context: Context) : SQLiteOpenHelper(context, MainDbCocktails.DATABASE_NAME, null, MainDbCocktails.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MainDbCocktails.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MainDbCocktails.SQL_DELETE_TABLE)
        onCreate(db)
    }
}