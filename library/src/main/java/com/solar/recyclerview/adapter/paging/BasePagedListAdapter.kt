package com.solar.recyclerview.adapter.paging

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.holder.ItemType

abstract class BasePagedListAdapter<T : ItemType, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagingDataAdapter<T, VH>(diffCallback) {
    override fun getItemViewType(position: Int): Int = getItem(position)?.layoutRes?:0

}
