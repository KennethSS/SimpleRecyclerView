package com.solar.recyclerviewsample.adapter

import androidx.recyclerview.widget.DiffUtil
import com.solar.recyclerview.adapter.paging.DataBindingPagedListAdapter
import com.solar.recyclerviewsample.model.food.Food

class PagingAdapter : DataBindingPagedListAdapter<Food>(null, DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Food>() {
            
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.title == newItem.title
            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.title == newItem.title
        }
    }
}