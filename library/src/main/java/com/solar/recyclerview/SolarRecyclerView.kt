package com.solar.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.DataBindingAdapter
import com.solar.recyclerview.adapter.DataBindingLoadStateAdapter

abstract class SolarRecyclerView<T: ItemType> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var threshold = 2

    var listener: RecyclerViewListener? = null

    var loadState: LoadState
        get() = loadStateAdapter.loadState
        set(value) {
            loadStateAdapter.loadState = value
        }

    abstract val viewModel: ViewModel

    var layoutManagerType: LayoutManagerType = LayoutManagerType.GRID

    var isReverse: Boolean = true

    var orientation = VERTICAL

    private val bindingAdapter: DataBindingAdapter<T>
            by lazy { DataBindingAdapter<T>(viewModel) }

    private val loadStateAdapter: DataBindingLoadStateAdapter
            by lazy { DataBindingLoadStateAdapter() }

    private var itemListener: ItemListener<T>? = null

    fun addMore(list: List<T>, isEnd: Boolean = false) {
        if (adapter == null) {
            adapter = ConcatAdapter(
                bindingAdapter,
                loadStateAdapter
            )
        }

        if (bindingAdapter.list.isEmpty()) {
            bindingAdapter.addAll(list)
        } else {
            bindingAdapter.list.addAll(list)
            //bindingAdapter.notifyDataSetChanged()

            Log.d("SolarRecyclerView", bindingAdapter.list.size.toString())
            Log.d("SolarRecyclerView", list.size.toString())

            bindingAdapter.notifyItemRangeChanged(bindingAdapter.list.size, list.size)
            bindingAdapter.notifyDataSetChanged()
        }

        loadStateAdapter.loadState = LoadState.NotLoading(isEnd)
    }

    fun add(item: T) {
        bindingAdapter.add(item)
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SolarRecyclerView,
            0, 0
        ).apply {
            try {
                val type = getInteger(R.styleable.SolarRecyclerView_layoutManagerType, 0)
                layoutManagerType = when(type) {
                    0 -> LayoutManagerType.LINEAR
                    1 -> LayoutManagerType.GRID
                    else -> LayoutManagerType.LINEAR
                }
            } finally {
                recycle()
            }
        }

        init(context)
    }

    private fun init(context: Context) {

        when(layoutManagerType) {
            LayoutManagerType.LINEAR -> {
                layoutManager = LinearLayoutManager(context).also {
                    it.orientation = orientation
                    it.reverseLayout = isReverse
                    //it.stackFromEnd = isReverse
                }
            }

            LayoutManagerType.GRID -> {
                layoutManager = GridLayoutManager(context, 3).also {
                    it.orientation = orientation
                }
            }

            LayoutManagerType.STAGGERED -> {

            }
        }


        addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(VERTICAL) && newState == SCROLL_STATE_IDLE) {
                    //recyclerView.smoothScrollToPosition(bindingAdapter.itemCount)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                layoutManager?.let { lm ->
                    val visibleItemCount = lm.childCount
                    val totalItemCount = lm.itemCount

                    val lastVisibleItemPosition = when(lm) {
                        is LinearLayoutManager -> lm.findLastVisibleItemPosition()
                        is GridLayoutManager -> lm.findLastVisibleItemPosition()
                        else -> return
                    }

                    if (visibleItemCount + lastVisibleItemPosition + threshold > totalItemCount) {
                        if (loadStateAdapter.loadState != LoadState.Loading) {
                            listener?.isEnd()
                            loadStateAdapter.loadState = LoadState.Loading
                        }
                    }
                }
            }
        })
    }

    interface RecyclerViewListener {
        fun isEnd()
    }
}