package com.example.mbazunovprojectcb.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mbazunovprojectcb.MainActivity
import com.example.mbazunovprojectcb.constants.Constants
import com.example.mbazunovprojectcb.database.MainDbManager
import com.example.mbazunovprojectcb.databinding.FragmentMyCocktailsBinding
import com.example.mbazunovprojectcb.model.ModelMain
import com.example.mbazunovprojectcb.recycler.CocktailItem
import com.example.mbazunovprojectcb.recycler.CocktailItemAdapter



class MyCocktails : Fragment(), CocktailItemAdapter.Listener {

    lateinit var binding: FragmentMyCocktailsBinding
    private val cocktailItemAdapter = CocktailItemAdapter(this)
    private val mainDbManager by lazy { MainDbManager( activity as MainActivity)} //DataBase //База Данных (БД)
    private val sharedPrefs by lazy { this.requireActivity().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMyCocktailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyOrNotDataBase()

        val rcView = binding.recyclerViewMyCocktailsMain
        ModelMain().startRecyclerView(rcView, cocktailItemAdapter = cocktailItemAdapter)

        mainDbManager.openDb() //run DataBase //создаем/открываем Базу Данных (БД) SQLite
        cocktailItemAdapter.addAllCocktail(mainDbManager.readDbData()) //обновляем список всех элементов из БД //??получаем все элементы из БД


        binding.fabMyCocktailsAddNewCocktails.setOnClickListener {
            ModelMain().addCocktailFragment(CreateCocktail.newInstance(), activity as MainActivity)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyCocktails()
    }

    fun emptyOrNotDataBase(){
        //получаем количество элементов в БД
        val shared = sharedPrefs.getInt(Constants.KEY_ITEM_COUNT, 0)

        //если БД не пустая показываем список элементов, иначе стартовую страницу
        if(shared > 0) {
            //if Database notEmpty -> change MyCocktails with Cocktail List
            binding.imageViewMyCocktailsPicture.visibility = View.GONE
            binding.imageViewArrow.visibility = View.GONE
            binding.textViewMyCocktailsInfo.visibility = View.GONE

            binding.recyclerViewMyCocktailsMain.visibility = View.VISIBLE
        }
        else {
            //else -> change Empty MyCocktails Fragment
            binding.imageViewMyCocktailsPicture.visibility = View.VISIBLE
            binding.imageViewArrow.visibility = View.VISIBLE
            binding.textViewMyCocktailsInfo.visibility = View.VISIBLE

            binding.recyclerViewMyCocktailsMain.visibility = View.GONE

        }
    }

    override fun openCocktailDetail(cocktailItem: CocktailItem) {
        Log.d("TAG1", cocktailItem.title)
        ModelMain().replaceFragment(cocktailItem, activity as MainActivity)
    }

    //DELETE-TEMP
    fun tempDataBase(): ArrayList<CocktailItem> {
        val dataList = ArrayList<CocktailItem>()

        dataList.add(CocktailItem(
            id = 0,
            img = "R.drawable.cocktail_1",
            title = "Mocktail",
            description = "razmoee",
            ingredients = "arara, atdsfs, asdfagf",
            recipe = ".........."
        ))
        dataList.add(CocktailItem(
            id = 1,
            img = "R.drawable.cocktail_2",
            title = "Mocktail hgfhf",
            description = "",
            ingredients = "arara, atdsfs, asdfagf",
            recipe = ".........."
        ))
        dataList.add(CocktailItem(
            id = 2,
            img = "R.drawable.cocktail_3",
            title = "Mo",
            description = "moee",
            ingredients = "arara, atds",
            recipe = ".........."
        ))
        dataList.add(CocktailItem(
            id = 3,
            img = "R.drawable.cocktail_4",
            title = "Mocktail",
            description = "razmoee",
            ingredients = "arara, atdsfs, asdfagf",
            recipe = ".........."
        ))
        dataList.add(CocktailItem(
            id = 4,
            img = "R.drawable.cocktail_5",
            title = "Mocdsadak",
            description = "razmoee",
            ingredients = "arafdsfsra, adsfsdftdsfs, asfsdfsdfagf",
        ))
        return dataList
    }
}