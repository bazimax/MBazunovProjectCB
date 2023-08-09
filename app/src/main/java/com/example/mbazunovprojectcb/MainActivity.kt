package com.example.mbazunovprojectcb

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mbazunovprojectcb.constants.Constants
import com.example.mbazunovprojectcb.database.MainDbManager
import com.example.mbazunovprojectcb.databinding.ActivityMainBinding
import com.example.mbazunovprojectcb.fragments.MyCocktails
import com.example.mbazunovprojectcb.recycler.CocktailItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainDbManager = MainDbManager(this) //DataBase //База Данных (БД)
    private val sharedPrefs by lazy {getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // ^ привязка

        mainDbManager.openDb() //run DataBase //создаем/открываем Базу Данных (БД) SQLite

        //стартовые настройки
        init()

        //mainDbManager.clearAllDataInDb()
        //tempDataBaseItem()

        //загружаем нужный фрагмент
        myCocktails()
    }

    override fun onDestroy() {
        mainDbManager.closeDb() //закрываем БД (доступ к БД?)
        super.onDestroy()
    }

    //start activity
    private fun myCocktails() {
        loadFragment(R.id.mainFrameLayout, MyCocktails.newInstance())
    }

    //DELETE-TEMP
    fun tempDataBaseItem(){
        mainDbManager.insertToDb(cocktailItem = CocktailItem(
            id = 0,
            img = "R.drawable.cocktail_1",
            title = "Mocktail",
            description = "razmoee",
            ingredients = "arara, atdsfs, asdfagf",
            recipe = ".........."
        ))
        mainDbManager.insertToDb(cocktailItem = CocktailItem(
            id = 1,
            img = "R.drawable.cocktail_2",
            title = "Mocktail",
            description = "",
            ingredients = "arara, atdsfs, asdfagf",
            recipe = ".........."
        ))
        mainDbManager.insertToDb(cocktailItem = CocktailItem(
            id = 2,
            img = "R.drawable.cocktail_3",
            title = "Mo",
            description = "moee",
            ingredients = "arara, atdsfs, asdfagf",
            recipe = ".........."
        ))
        mainDbManager.insertToDb(cocktailItem = CocktailItem(
            id = 3,
            img = "R.drawable.cocktail_4",
            title = "Mocktail",
            description = "razmoee",
            ingredients = "arara, atdsfs, asdfagf",
            recipe = ".........."
        ))
        mainDbManager.insertToDb(cocktailItem = CocktailItem(
            id = 4,
            img = "R.drawable.cocktail_5",
            title = "Mocdsadak",
            description = "razmoee",
            ingredients = "arafdsfsra, adsfsdftdsfs, asfsdfsdfagf",
        ))
    }

    //кнопка назад
    //Back button
    private var backPressed: Long = 0
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            //повторный запрос на выход из приложения.
            val messageExitApp = resources.getString(R.string.exitApp)

            if (backPressed + 2000 > System.currentTimeMillis()) super.onBackPressed() else Toast.makeText(
                baseContext, messageExitApp,
                Toast.LENGTH_SHORT
            ).show()
            backPressed = System.currentTimeMillis()
        }
        else super.onBackPressed()
    }

    //загружаем фрагмент
    //load fragment
    private fun loadFragment(idFrameLayoutFragment: Int, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(idFrameLayoutFragment, fragment)
            .commit()
    }

    fun init(){
        //для MyCocktails-Fragment
        if(mainDbManager.count() > 0) {
            //if Database notEmpty -> start MyCocktails Fragment
            //если БД не пустая, то записываем 1
            sharedPrefs.edit().putInt(Constants.KEY_ITEM_COUNT, 1).apply()
        }
        else {
            //else -> start Empty-MyCocktails Fragment
            //иначе записываем 0
            sharedPrefs.edit().putInt(Constants.KEY_ITEM_COUNT, 0).apply()
        }
    }
}