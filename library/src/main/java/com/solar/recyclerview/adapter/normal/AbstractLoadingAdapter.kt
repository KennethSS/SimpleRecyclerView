package com.solar.recyclerview.adapter.normal

import com.solar.recyclerview.R
import com.solar.recyclerview.RecyclerViewController
import com.solar.recyclerview.adapter.holder.BindingHolder
import com.solar.recyclerview.adapter.holder.ItemType

abstract class AbstractLoadingAdapter<T : ItemType>(
    var controller: RecyclerViewController? = null
) : AbstractDataBindingAdapter<T>() {

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        if (controller?.isLoading == true && position == list.size) {
            return
        } else {
            super.onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (controller?.isLoading == true && position == list.size) R.layout.item_loading
        else getItem(position).layoutRes

    override fun getItemCount(): Int = if (controller?.isLoading == true) list.size + 1 else list.size
}