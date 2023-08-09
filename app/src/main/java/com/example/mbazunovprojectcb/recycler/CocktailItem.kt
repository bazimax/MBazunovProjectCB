package com.example.mbazunovprojectcb.recycler

data class CocktailItemList(
    val list: ArrayList<CocktailItem>
)

data class CocktailItem(
    var id: Int = 0,
    val date: String = "base",
    val img: String = "",
    val title: String = "",
    val description: String = "",
    val ingredients: String = "",
    val recipe: String = ""
)
