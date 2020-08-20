package com.moralesjuan.yourticketapp.categorias

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moralesjuan.yourticketapp.R
import kotlinx.android.synthetic.main.item_row_category.view.*

class MainAdapterCategory(private val context: Context): RecyclerView.Adapter<MainAdapterCategory.MainViewHolderCategory>(){

    private var dataList = mutableListOf<Category>();

    fun setListData(data:MutableList<Category>){
        dataList = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderCategory {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_category, parent, false)
        return MainViewHolderCategory(view)
    }

    override fun getItemCount(): Int {
        if (dataList.size > 0) {
            return dataList.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: MainViewHolderCategory, position: Int) {
        val category = dataList[position]
        holder.bindView(category)
    }

    inner class MainViewHolderCategory(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(category: Category){
            itemView.textViewCategory.text = category.nombre_cat
            Glide.with(context).load(category.path_imagen).into(itemView.imageViewCategorya)
        }
    }


}