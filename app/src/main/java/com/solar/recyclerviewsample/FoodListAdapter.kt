package com.solar.recyclerviewsample

import androidx.lifecycle.ViewModel
import com.solar.recyclerview.R
import com.solar.recyclerview.adapter.normal.DataBindingAdapter
import com.solar.recyclerview.adapter.holder.BindingHolder

/**
 *  Created by Kenneth on 12/29/20
 */
class FoodListAdapter(viewModel: ViewModel?) : DataBindingAdapter<FoodGrid>(viewModel = viewModel) {
    var isLoading: Boolean = true

    override fun onBindViewHolder(holder: BindingHolder<FoodGrid>, position: Int) {
        if (isLoading && position == list.size) {

        } else {
            super.onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == list.size) {
            R.layout.item_loading
        } else {
            getItem(position).layoutRes
        }
    }

    override fun getItemCount(): Int = if (isLoading) list.size + 1 else list.size
}