package com.example.mbazunovprojectcb.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mbazunovprojectcb.R
import com.example.mbazunovprojectcb.databinding.RecyclerViewCoctailItemBinding


class CocktailItemAdapter(private val listener: Listener): RecyclerView.Adapter<CocktailItemAdapter.CocktailItemHolder>() {

    private val cocktailItemList = ArrayList<CocktailItem>()

    class CocktailItemHolder(item: View): RecyclerView.ViewHolder(item) {

        private val binding = RecyclerViewCoctailItemBinding.bind(item)//использует cardView
        fun bind(cocktailItem: CocktailItem, listener: Listener) = with (binding){

            imageViewCoctailItem.setImageResource(cocktailItem.img.toInt()) //
            textViewCoctailItem.text = cocktailItem.title

            cardViewCoctailItem.setOnClickListener{
                listener.openCocktailDetail(cocktailItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_coctail_item, parent, false) //использует cardView //R.layout.recycler_view_news_item
        return CocktailItemHolder(view)
    }

    override fun onBindViewHolder(holder: CocktailItemHolder, position: Int) {
        holder.bind(cocktailItemList[position], listener)
    }

    override fun getItemCount(): Int {
        return cocktailItemList.size
    }

    fun addAllCocktail(listCocktailItem: List<CocktailItem>){
        cocktailItemList.clear()
        cocktailItemList.addAll(listCocktailItem)
        notifyDataSetChanged()
    }

    interface Listener {
        fun openCocktailDetail(cocktailItem: CocktailItem)
    }
}