package com.solar.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.ItemType

abstract class BaseListAdapter<T : ItemType, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {
    override fun getItemViewType(position: Int): Int = getItem(position).layoutRes
}
