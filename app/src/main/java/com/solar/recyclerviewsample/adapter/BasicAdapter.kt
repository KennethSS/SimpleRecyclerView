package com.solar.recyclerviewsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerviewsample.R
import com.solar.recyclerviewsample.viewholder.BasicViewHolder

class BasicAdapter : RecyclerView.Adapter<BasicViewHolder>() {

    val items: List<String> = List(5) { "$it item" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_menu, parent, false)

        return BasicViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


}