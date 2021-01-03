package com.solar.recyclerview.adapter.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.solar.recyclerview.adapter.holder.ItemType
import com.solar.recyclerview.adapter.holder.BindingHolder

class DataBindingListAdapter<T : ItemType>(private val viewModel: ViewModel, diffCallback: DiffUtil.ItemCallback<T>)
    : BaseListAdapter<T, BindingHolder<T>>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            viewType,
            parent,
            false
        )
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        holder.bind(getItem(position), position, viewModel)
    }
}