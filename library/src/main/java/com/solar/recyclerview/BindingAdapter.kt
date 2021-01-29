package com.solar.recyclerview

import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.decoration.SolarItemDecoration
import com.solar.recyclerview.adapter.normal.AbstractLoadingAdapter

/**
 *  Created by Kenneth on 12/29/20
 */
@BindingAdapter(
    "adapter",
    "vm",
    "loading",
    requireAll = false)
fun setItems(rv: RecyclerView,
             adapter: RecyclerView.Adapter<*>,
             vm: ViewModel,
             loading: Boolean = true) {
    RecyclerViewController(
        rv,
        adapter = adapter,
        onAttachDestination = { }, //vm.fetchList() },
        isLoading = loading,
        isAttachedThreshold = false).also {

        if (adapter is AbstractLoadingAdapter<*>) {
            adapter.controller = it
        }
    }
}

@BindingAdapter("bind:decoration")
fun setDecoration(view: RecyclerView, space: Int) {
    view.addItemDecoration(SolarItemDecoration(space, 3))
}