package com.solar.recyclerview.adapter

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.ItemType

abstract class BasePagedListAdapter<T : ItemType, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, VH>(diffCallback) {
    override fun getItemViewType(position: Int): Int = getItem(position)?.layoutRes?:0
}
