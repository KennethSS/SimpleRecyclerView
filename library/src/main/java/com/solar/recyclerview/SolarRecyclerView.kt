package com.solar.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.DataBindingAdapter

abstract class SolarRecyclerView<T: ItemType> : RecyclerView {

    abstract val viewModel: ViewModel

    private val bindingAdapter: DataBindingAdapter<T> by lazy { DataBindingAdapter<T>(viewModel) }
    private var itemListener: ItemListener<T>? = null

    fun submit(list: List<T>) {
        adapter = bindingAdapter
        bindingAdapter.submitList(list)
    }

    fun add(item: T) {
        bindingAdapter.add(item)
    }

    fun addAll(list: List<T>) {
        bindingAdapter.addAll(list)
    }

    fun setOnItemListener(itemListener: ItemListener<T>) {
        this.itemListener = itemListener
    }

    private fun init(context: Context) {
        layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
    }

    constructor(context: Context) : super(context) { init(context) }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { init(context) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) { init(context) }
}