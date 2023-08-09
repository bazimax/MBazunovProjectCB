package com.example.mbazunovprojectcb.model

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mbazunovprojectcb.MainActivity
import com.example.mbazunovprojectcb.constants.Constants
import com.example.mbazunovprojectcb.database.MainDbManager
import com.example.mbazunovprojectcb.fragments.MyCocktails
import com.example.mbazunovprojectcb.fragments.MyCocktailsDetail
import com.example.mbazunovprojectcb.recycler.CocktailItem
import com.example.mbazunovprojectcb.recycler.CocktailItemAdapter

class ModelMain() {

    fun startRecyclerView(rcView: RecyclerView, cocktailItemAdapter: CocktailItemAdapter){
        Log.d("TAG1", "startRecyclerView: start")
        rcView.setHasFixedSize(true) //для оптимизации?
        rcView.layoutManager = GridLayoutManager(rcView.context, 2) //проверить
        rcView.adapter = cocktailItemAdapter
    }

    //!!
    fun addCocktailFragment(fragment: Fragment, activity: MainActivity, title: String = "") {
        if (title != "") {
            //если имя коктейля не пустое (т.е. мы изменяем элемент), то записываем в SharedPref для дальнейшей передачи
            val sharedPrefs = activity.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
            sharedPrefs.edit().putString(Constants.KEY_DETAIL_ITEM, title).apply()
        }

        activity.supportFragmentManager
            .beginTransaction()
            .replace(com.example.mbazunovprojectcb.R.id.mainFrameLayout, fragment)
            .addToBackStack("main")
            .commit()
    }

    fun saveCocktail(cocktailItem: CocktailItem,  mainDbManager: MainDbManager, activity: MainActivity) {

        mainDbManager.openDb() //run DataBase //создаем/открываем Базу Данных (БД) SQLite

        mainDbManager.insertToDb(cocktailItem)

        //после добавления нового элемента увеличиваем количество элементов, чтобы MyCocktails запускался со списком
        val sharedPrefs = activity.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        sharedPrefs.edit().putInt(Constants.KEY_ITEM_COUNT, 1).apply()

        //cocktailItemAdapter.addAllCocktail(mainDbManager.readDbData()) //обновляем список всех элементов из БД

        activity.supportFragmentManager
            .beginTransaction()
            .replace(com.example.mbazunovprojectcb.R.id.mainFrameLayout, MyCocktails.newInstance())
            //.addToBackStack("main")
            .commit()
    }

    fun deleteCocktail(cocktailItem: CocktailItem, mainDbManager: MainDbManager, activity: MainActivity) {
        mainDbManager.openDb() //run DataBase //создаем/открываем Базу Данных (БД) SQLite

        mainDbManager.deleteDbElement(cocktailItem)

        mainDbManager.count()

        //после удаления коктейля проверяем, не нужно ли теперь показывать пустой MyCocktails
        val sharedPrefs = activity.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        sharedPrefs.edit().putInt(Constants.KEY_ITEM_COUNT, mainDbManager.count()).apply()

        //cocktailItemAdapter.addAllCocktail(mainDbManager.readDbData()) //обновляем список всех элементов из БД


        activity.supportFragmentManager
            .beginTransaction()
            .replace(com.example.mbazunovprojectcb.R.id.mainFrameLayout, MyCocktails.newInstance())
            //.addToBackStack("main")
            .commit()
    }

    //!!
    fun replaceFragment(cocktailItem: CocktailItem, activity: MainActivity) {
        //записываем значение для передачи дальше
        val sharedPrefs = activity.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        sharedPrefs.edit().putString(Constants.KEY_DETAIL_ITEM, cocktailItem.title).apply()

        activity.supportFragmentManager
            .beginTransaction()
            .replace(com.example.mbazunovprojectcb.R.id.mainFrameLayout, MyCocktailsDetail.newInstance())
            .addToBackStack("main")
            .commit()
    }
}

