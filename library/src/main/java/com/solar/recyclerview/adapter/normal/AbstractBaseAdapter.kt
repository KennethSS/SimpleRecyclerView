package com.solar.recyclerview.adapter.normal

import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.ViewModelList
import com.solar.recyclerview.adapter.holder.ItemType

/**
 *  This is Basic Class for RecyclerView Adapter
 */

abstract class AbstractBaseAdapter<T : ItemType, VH : RecyclerView.ViewHolder>(
    private val viewModelList: ViewModelList<T>
) : RecyclerView.Adapter<VH>() {

    fun add(item: T) {
        viewModelList.list.value?.add(item)
        notifyItemInserted(itemCount)
    }

    fun addAt(index: Int, item: T) {
        if (index < viewModelList.list.value?.size ?: 0) {
            viewModelList.list.value?.add(index, item)
            notifyItemInserted(index)
        }
    }

    fun addAll(list: List<T>) {
        val previous = viewModelList.list.value?.size ?: 0
        viewModelList.list.value?.addAll(list)
        notifyItemRangeInserted(previous, list.size)
    }

    fun remove(item: T, isAnim: Boolean = false) {
        val index = viewModelList.list.value?.indexOf(item) ?: 0
        if (index != -1) {
            viewModelList.list.value?.removeAt(index)

            if (isAnim) notifyItemRangeRemoved(index, 1)
            else notifyDataSetChanged()
        }
    }

    fun removeAt(position: Int, isAnim: Boolean = false) {
        if (position < viewModelList.list.value?.size ?: 0) {
            viewModelList.list.value?.removeAt(position)

            if (isAnim) notifyItemRangeRemoved(position, 1)
            else notifyDataSetChanged()
        }
    }

    fun submitList(list: List<T>) {
        viewModelList.list.value?.run {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = viewModelList.list.value?.size ?: 0

    override fun getItemViewType(position: Int): Int = viewModelList.list.value?.get(position)?.layoutRes ?: 0
}