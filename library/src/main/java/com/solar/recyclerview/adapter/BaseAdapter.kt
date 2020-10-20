package com.solar.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.ItemType

/**
 *  This is Basic Class for RecyclerView Adapter
 */

abstract class BaseAdapter<T : ItemType, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    open val list: MutableList<T> = mutableListOf()

    fun add(item: T) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun addAt(index: Int, item: T) {
        if (index < list.size) {
            list.add(index, item)
            notifyDataSetChanged()
        }
    }

    fun addAll(list: List<T>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }



    fun remove(item: T) {
        val index = list.indexOf(item)
        if (index != -1) {
            list.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun removeAt(position: Int) {
        if (position < list.size) {
            list.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun update(index: Int, item: T) {
        if (index < list.size) {
            list[index] = item
            notifyDataSetChanged()
        }
    }

    fun submitList(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(index: Int): T = list[index]

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = list[position].layoutRes
}