package com.example.mbazunovprojectcb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mbazunovprojectcb.MainActivity
import com.example.mbazunovprojectcb.constants.Constants
import com.example.mbazunovprojectcb.database.MainDbManager
import com.example.mbazunovprojectcb.databinding.FragmentMyCocktailsDetailBinding
import com.example.mbazunovprojectcb.model.ModelMain
import com.example.mbazunovprojectcb.recycler.CocktailItem


class MyCocktailsDetail : Fragment() {

    lateinit var binding: FragmentMyCocktailsDetailBinding
    private val mainDbManager by lazy { MainDbManager( activity as MainActivity) } //DataBase //База Данных (БД)
    //private val sharedPrefs by lazy { this.requireActivity().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMyCocktailsDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        binding.buttonMyCocktailDetailEdit.setOnClickListener {
            //для редактирования запускается тот же фрагмент, что и для нового коктейля, а значения должны подхватится
            val title = binding.textViewCocktailDetailTitle.text.toString()
            ModelMain().addCocktailFragment(CreateCocktail.newInstance(), activity as MainActivity, title)
        }

        binding.buttonMyCocktailDetailDelete.setOnClickListener {
            //для редактирования запускается тот же фрагмент, что и для нового коктейля, а значения должны подхватится
            val title = binding.textViewCocktailDetailTitle.text.toString()
            ModelMain().deleteCocktail(CocktailItem(title = title), mainDbManager, activity as MainActivity)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyCocktailsDetail()
    }

    private fun init() {
        mainDbManager.openDb()

        val sharedPrefs = activity?.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        //получаем имя нужного коктейля
        val title = sharedPrefs?.getString(Constants.KEY_DETAIL_ITEM, "")
        if (title != null) {
            //находим коктейль в БД
            val item = mainDbManager.findItemInDb(title)[0]

            //если нет рецепта -> скрываем
            if (item.recipe == "") {
                binding.textViewCocktailRecipeTitle.visibility = View.GONE
                binding.textViewCocktailRecipeContent.visibility = View.GONE
            }

            if (item.description == "") {
                binding.textViewCocktailDetailDescription.visibility = View.GONE
            }

            binding.textViewCocktailDetailTitle.text = item.title
            binding.textViewCocktailDetailDescription.text = item.description
            binding.textViewCocktailIngridients.text = item.ingredients
            binding.textViewCocktailRecipeContent.text = item.recipe
            binding.imageViewPhoto.setImageResource(item.img.toInt())
        }

    }
}