package com.example.bgg_stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.bgg_stats.databinding.ActivityMainBinding
import com.example.bgg_stats.fragment.MainFragment
import com.example.bgg_stats.objects.Constants
import com.example.bgg_stats.vm.ViewModel

class MainActivity : AppCompatActivity() {
    private val logNameClass = "MainActivity" //для логов

    //КОНСТАНТЫ
    companion object {
        //log
        const val TAG = Constants.TAG //разное
        const val TAG_DEBUG = Constants.TAG_DEBUG //запуск функция, активити и тд
        const val TAG_DATA = Constants.TAG_DATA //переменные и данные

        //SharedPreferences
        const val SEARCH_ITEM = Constants.SEARCH_ITEM//"search"
        const val SHARED_INSTRUCTION = Constants.SHARED_INSTRUCTION//"instruction"

        //Имена файлов
        const val FILE_SEARCH_ITEM = Constants.FILE_SEARCH_ITEM//"searchItems.json"
    }

    private lateinit var binding: ActivityMainBinding
    private val vm: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // ^ привязка

        Log.d(TAG_DEBUG, "$logNameClass ======================== >\n======\n======\n======\n====== LAUNCH ")

        loadFragment(R.id.mainActivityFrameLayoutMain, MainFragment.newInstance())
    }

    //стартовые функции
    private fun init() {
        //BACKUP//generateSearchItemArrayList() //
        Log.d(TAG_DEBUG, "$logNameClass >f init === START")
    }

    //загружаем фрагмент
    //load fragment
    private fun loadFragment(idFrameLayoutFragment: Int, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(idFrameLayoutFragment, fragment)
            .commit()
    }
}