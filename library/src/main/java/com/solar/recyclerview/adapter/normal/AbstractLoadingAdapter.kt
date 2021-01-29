package com.solar.recyclerview.adapter.normal

import com.solar.recyclerview.RecyclerViewController
import com.solar.recyclerview.ViewModelList
import com.solar.recyclerview.adapter.holder.BindingHolder
import com.solar.recyclerview.adapter.holder.ItemType

abstract class AbstractLoadingAdapter<T : ItemType>(
    private val viewModelList: ViewModelList<T>,
    var controller: RecyclerViewController? = null
) : AbstractDataBindingAdapter<T>(viewModelList) {

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        /*if (controller?.isLoading == true && position == list.size) {
            return
        } else {
            super.onBindViewHolder(holder, position)
        }*/
    }

    /*override fun getItemViewType(position: Int): Int =
        if (controller?.isLoading == true && position == list.size) R.layout.item_loading
        else getItem(position).layoutRes*/

    override fun getItemCount(): Int = 0 //if (controller?.isLoading == true) list.size + 1 else list.size
}