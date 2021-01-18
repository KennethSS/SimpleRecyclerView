package com.solar.recyclerview.adapter.normal

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.holder.BindingHolder
import com.solar.recyclerview.adapter.holder.ItemType

abstract class AbstractDataBindingAdapter<T : ItemType>(
    private val layoutManager: RecyclerView.LayoutManager? = null,
    private val viewModel: ViewModel? = null,
    private val callbackLayoutManager: (( (lm: RecyclerView.LayoutManager) -> Unit) -> Unit)? = null
) : AbstractBaseAdapter<T, BindingHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return BindingHolder(biding)
    }

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        Log.d("onBindViewHolder", "onBindViewHolder $position")
        if (list.isNotEmpty()) {
            holder.bind(list[position], position, viewModel)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).layoutRes
}