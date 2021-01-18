package com.solar.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.listener.LoadMoreScrollListener

class RecyclerViewController(
    rv: RecyclerView,
    adapter: RecyclerView.Adapter<*>,
    onAttachDestination: () -> Unit,
    var isLoading: Boolean = false,
    var isAttachedThreshold: Boolean = false
) {
    init {
        rv.adapter = adapter
        rv.addOnScrollListener(LoadMoreScrollListener(this, onAttachDestination))
    }
}
