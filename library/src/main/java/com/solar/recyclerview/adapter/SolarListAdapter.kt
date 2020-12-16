package com.solar.recyclerview.adapter

import androidx.lifecycle.ViewModel
import com.solar.recyclerview.ItemType
import com.solar.recyclerview.R
import com.solar.recyclerview.adapter.normal.DataBindingAdapter
import com.solar.recyclerview.holder.BindingHolder

/**
 *  Created by Kenneth on 2020/11/20
 */
class SolarListAdapter<T: ItemType>(viewModel: ViewModel?) : DataBindingAdapter<T>(viewModel) {
    var isLoading: Boolean = true

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
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