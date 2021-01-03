package com.solar.recyclerview

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.decoration.SolarItemDecoration
import com.solar.recyclerview.adapter.holder.ItemType

/**
 *  Created by Kenneth on 12/29/20
 */
@BindingAdapter("bind:loading", "bind:vm", "bind:items", requireAll = false)
fun initSolarRecyclerView(
    rv: SolarRecyclerView,
    loading: Boolean,
    vm: ViewModel?,
    items: List<ItemType>?
) {
    items?.let {
        rv.loadMore(items, vm, loading)
    } ?: rv.loadMore(listOf(), vm, loading)
}

@BindingAdapter("bind:decoration")
fun setDecoration(view: RecyclerView, space: Int) {
    view.addItemDecoration(SolarItemDecoration(space, 3))
}