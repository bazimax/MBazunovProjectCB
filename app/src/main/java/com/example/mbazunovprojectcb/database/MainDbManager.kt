package com.example.mbazunovprojectcb.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.mbazunovprojectcb.recycler.CocktailItem


class MainDbManager(context: Context) {

    private val mainDbHelper = MainDbHelper(context) //может открывать БД и ид
    private var db : SQLiteDatabase? = null //через mainDbHelper работает с БД

    //открываем-запускаем Базу Данных
    //open DataBase
    fun openDb(){
        db = mainDbHelper.writableDatabase
    }

    //закрытие Базы Данных
    //close DataBase
    fun closeDb(){
        mainDbHelper.close()
    }

    //очистка всей Базы Данных
    //clear all items in DataBase
    fun clearAllDataInDb(){
        db?.delete(MainDbCocktails.TABLE_NAME, null, null)
    }

    //добавляем данные в Базу Данных
    //!!add item of DataBase
    fun insertToDb(cocktailItem: CocktailItem) {
        //!! нужно добавить проверку на имя коктейля(если такой коктейль уже есть в базе данных)
        //!! need to add check name cocktail (if DataBase has the cocktail with same name)
        val values = values(cocktailItem)
        db?.insert(MainDbCocktails.TABLE_NAME, null, values)
    }

    fun insertToDbOld(cocktailItem: CocktailItem) : Boolean {
        //!! нужно добавить проверку на имя коктейля(если такой коктейль уже есть в базе данных)
        //!! need to add check name cocktail (if DataBase has the cocktail with same name)
        val findSameTitle = findItemInDb(cocktailItem.title)
        return if (findSameTitle.isNotEmpty()) {
            //с таким именем коктейль уже есть в бд
            //a cocktail with same name is already exists in the DataBase
            false
        } else {
            //это новый коктейль - записываем в бд
            //this is new cocktail - write to DataBase
            val values = values(cocktailItem)
            db?.insert(MainDbCocktails.TABLE_NAME, null, values)
            true
        }
    }

    //обновляем элемент БД
    //update item of DataBase
    fun updateDbElement(cocktailItem: CocktailItem){
        val values = values(cocktailItem)
        db?.update(MainDbCocktails.TABLE_NAME, values, "${MainDbCocktails.COLUMN_NAME_TITLE} = ?", arrayOf(cocktailItem.title))
    }

    //удаляем элемент/строку из БД
    //delete item of DataBase
    fun deleteDbElement(cocktailItem: CocktailItem){
        db?.delete(MainDbCocktails.TABLE_NAME, "${MainDbCocktails.COLUMN_NAME_TITLE} = ?", arrayOf(cocktailItem.title))
    }

    //чтение Базы Данных
    //read DataBase
    fun readDbData() : ArrayList<CocktailItem>{

        val cursor = db?.query(MainDbCocktails.TABLE_NAME, null, null, null, null, null, null)
        val dataList = itemList(cursor)

        cursor?.close()

        //сортировка dataList
        //sortByDate(dataList)

        return dataList
    }

    //count items in DataBase
    fun count(): Int {
        //val numRows = DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM ${MainDbCocktails.TABLE_NAME}", null).toInt() //v2
        return DatabaseUtils.queryNumEntries(db, MainDbCocktails.TABLE_NAME).toInt()
    }

    //ищем совпадения в БД
    fun findItemInDb(title: String): ArrayList<CocktailItem>{

        val tableName = MainDbCocktails.TABLE_NAME
        val selection = "${MainDbCocktails.COLUMN_NAME_TITLE} LIKE '%' || ? || '%'"
        //V2
        //val selectQuery5 = "SELECT * FROM $tableName WHERE $column LIKE '%' || :string || '%'"
        val selectionArgs = arrayOf(title)//arrayOf("9422089")

        val cursor = db?.query(tableName, null, selection, selectionArgs, null, null, null)
        val dataList = itemList(cursor)

        cursor?.close()


        //сортировка dataList
        //sortByDate(dataList)

        Log.d("TAG1", "MainDBManager >f findItemInDb ----- END")
        return dataList
    }

    //сортируем массив по дате
    private fun sortByDate(dataList: ArrayList<CocktailItem>): ArrayList<CocktailItem>{
        dataList.sortByDescending{it.date}
        return dataList
    }

    //cursor
    @SuppressLint("Range")
    private fun itemList(cursor: Cursor?) : ArrayList<CocktailItem> {
        Log.d("TAG1", "MainDBManager >f itemList === START")

        val dataList = ArrayList<CocktailItem>()
        while (cursor?.moveToNext()!!){

            //Log.d("TAG1", "readDbData > Start while")
            val date = cursor.getString(cursor.getColumnIndex(MainDbCocktails.COLUMN_NAME_DATE)).toString()
            //Log.d("TAG1", "readDbData > val 1")
            val img = cursor.getString(cursor.getColumnIndex(MainDbCocktails.COLUMN_NAME_IMG)).toString()
            //Log.d("TAG1", "readDbData > val 3")
            val title = cursor.getString(cursor.getColumnIndex(MainDbCocktails.COLUMN_NAME_TITLE)).toString()
            //Log.d("TAG1", "readDbData > val 4")
            val description = cursor.getString(cursor.getColumnIndex(MainDbCocktails.COLUMN_NAME_DESCRIPTION)).toString()
            //Log.d("TAG1", "readDbData > val 5")
            val ingredients = cursor.getString(cursor.getColumnIndex(MainDbCocktails.COLUMN_NAME_INGREDIENTS)).toString()

            val recipe = cursor.getString(cursor.getColumnIndex(MainDbCocktails.COLUMN_NAME_RECIPE)).toString()

            //val id = cursor.getColumnIndex(MainDbNameObject.COLUMN_NAME_STATUS_SAVED).toString()
            val id: Int = cursor.position //записываем id
            //Log.d("TAG1", "readDbData > INDEX > ${cursor.position}")

            val newsItem = CocktailItem(id, date, img, title, description, ingredients, recipe)
            dataList.add(newsItem)
            //Log.d("TAG1", "readDbData > while ------------END ??")
        }
        return dataList
    }

    private fun values (cocktailItem: CocktailItem) : ContentValues {
        Log.d("TAG1", "MainDBManager >f values === START")
        return ContentValues().apply {
            put(MainDbCocktails.COLUMN_NAME_DATE, cocktailItem.date)
            put(MainDbCocktails.COLUMN_NAME_IMG, cocktailItem.img)
            put(MainDbCocktails.COLUMN_NAME_TITLE, cocktailItem.title)
            put(MainDbCocktails.COLUMN_NAME_DESCRIPTION, cocktailItem.description)
            put(MainDbCocktails.COLUMN_NAME_INGREDIENTS, cocktailItem.ingredients)
            put(MainDbCocktails.COLUMN_NAME_RECIPE, cocktailItem.recipe)
        }
    }

}