package com.solar.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.ItemType
import com.solar.recyclerview.R
import com.solar.recyclerview.holder.BindingHolder

class DataBindingLoadStateAdapter : LoadStateAdapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_loading, parent, false)
        return LoadStateHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {

    }

    private inner class LoadStateHolder(view: View) : RecyclerView.ViewHolder(view)
}