package com.solar.recyclerviewsample.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerviewsample.R

class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val title: TextView = itemView.findViewById(R.id.item_food_title)

    fun bind(str: String) {
        title.text = str
    }
}