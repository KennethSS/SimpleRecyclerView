package com.solar.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import com.solar.recyclerview.ItemType
import com.solar.recyclerview.holder.BindingHolder

class DataBindingPagedListAdapter<T : ItemType>(private val viewModel: ViewModel, diffCallback: DiffUtil.ItemCallback<T>)
    : BasePagedListAdapter<T, BindingHolder<T>>(diffCallback) {
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
        getItem(position)?.let {
            holder.bind(it, position, viewModel)
        }
    }
}