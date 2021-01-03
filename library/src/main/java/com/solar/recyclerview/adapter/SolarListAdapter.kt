package com.solar.recyclerview.adapter

import androidx.lifecycle.ViewModel
import com.solar.recyclerview.R
import com.solar.recyclerview.RecyclerViewController
import com.solar.recyclerview.adapter.holder.BindingHolder
import com.solar.recyclerview.adapter.holder.ItemType
import com.solar.recyclerview.adapter.normal.DataBindingAdapter

/**
 *  Created by Kenneth on 2020/11/20
 */
class SolarListAdapter<T : ItemType>(
    private val controller: RecyclerViewController,
    viewModel: ViewModel? = null
) : DataBindingAdapter<T>(viewModel = viewModel) {

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        if (controller.isLoading && position == list.size) {

        } else {
            super.onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (controller.isLoading && position == list.size) {
            R.layout.item_loading
        } else {
            getItem(position).layoutRes
        }
    }

    override fun getItemCount(): Int = if (controller.isLoading) list.size + 1 else list.size
}