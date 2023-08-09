package com.example.mbazunovprojectcb.database

import android.provider.BaseColumns

object MainDbCocktails : BaseColumns {
    const val TABLE_NAME = "cocktails_table"
    const val COLUMN_NAME_DATE = "date"
    const val COLUMN_NAME_IMG = "img"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_DESCRIPTION = "description"
    const val COLUMN_NAME_INGREDIENTS = "ingredients"
    const val COLUMN_NAME_RECIPE = "recipe"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MainDbCocktails.db"

    const val SQL_CREATE_TABLE =
        "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_NAME_DATE TEXT," +
                "$COLUMN_NAME_IMG TEXT," +
                "$COLUMN_NAME_TITLE TEXT," +
                "$COLUMN_NAME_DESCRIPTION TEXT," +
                "$COLUMN_NAME_INGREDIENTS TEXT," +
                "$COLUMN_NAME_RECIPE)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}