package com.example.mbazunovprojectcb.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mbazunovprojectcb.MainActivity
import com.example.mbazunovprojectcb.R
import com.example.mbazunovprojectcb.constants.Constants
import com.example.mbazunovprojectcb.database.MainDbManager
import com.example.mbazunovprojectcb.databinding.FragmentCreateCocktailBinding
import com.example.mbazunovprojectcb.model.ModelMain
import com.example.mbazunovprojectcb.recycler.CocktailItem
import kotlin.random.Random


class CreateCocktail : Fragment() {

    lateinit var binding: FragmentCreateCocktailBinding
    private val mainDbManager by lazy { MainDbManager( activity as MainActivity) } //DataBase //База Данных (БД)
    private val sharedPrefs by lazy {
        this.requireActivity().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateCocktailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*
        //В работе
        //если изменяем элемент то берем данные из БД
        val sharedPrefs = activity?.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        val itemTitle = sharedPrefs?.getString(Constants.KEY_DETAIL_ITEM, "")

        if(itemTitle == "")
        */

        val img = when (Random.nextInt(1, 5)) {
            1 -> R.drawable.cocktail_1
            2 -> R.drawable.cocktail_2
            3 -> R.drawable.cocktail_3
            4 -> R.drawable.cocktail_4
            else -> R.drawable.cocktail_5
        }

        binding.imageView.setImageResource(img)

        //save cocktail
        binding.buttonCreateCocktailSave.setOnClickListener {

            val title = binding.textInputTitle.text.toString()
            val description = binding.textInputDescription.text.toString()
            val ingredients = binding.textInputIngredients.text.toString()
            val recipe = binding.textInputRecipe.text.toString()
            //val random = Random.nextInt(1, 5)
            //val img = "@drawable/cocktail_$random" //"@drawable/cocktail_1" //R.drawable.cocktail_1

            if (title == "") binding.textViewErrorTitle.visibility = View.VISIBLE
            else if (ingredients == "") binding.textViewErrorIngridients.visibility = View.VISIBLE
            else {
                Log.d("TAG1", "textInputTitle: ${binding.textInputTitle.text}")
                Log.d("TAG1", "Img: $img")

                val cocktailItem = CocktailItem(
                    img = img.toString(),
                    title = title,
                    description = description,
                    ingredients = ingredients,
                    recipe = recipe
                )

                //save cocktail
                ModelMain().saveCocktail(cocktailItem, mainDbManager, activity as MainActivity)
            }


        }

        binding.buttonCreateCocktailCancel.setOnClickListener {
            //для редактирования запускается тот же фрагмент, что и для нового коктейля, а значения должны подхватится
            ModelMain().addCocktailFragment(MyCocktails.newInstance(), activity as MainActivity)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CreateCocktail()
    }
}