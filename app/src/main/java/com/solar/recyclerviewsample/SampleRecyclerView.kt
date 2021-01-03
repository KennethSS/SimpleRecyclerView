package com.solar.recyclerviewsample

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.paging.LoadState
import androidx.recyclerview.widget.*
import com.solar.recyclerview.R
import com.solar.recyclerview.adapter.DataBindingLoadStateAdapter
import com.solar.recyclerview.attr.SnapType
import kotlin.math.abs

class SampleRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    /*if (visibleItemCount + lastVisibleItemPosition + threshold > totalItemCount) {
        if (loadStateAdapter.loadState != LoadState.Loading && !loadStateAdapter.loadState.endOfPaginationReached) {
            post {
                loadStateAdapter.loadState = LoadState.Loading
            }
        }
    }*/

    var loadState: LoadState
        get() = loadStateAdapter.loadState
        set(value) {
            loadStateAdapter.loadState = value
        }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        restorePosition()
    }

    var isReverse: Boolean = true

    private var mLayoutManagerSavedState: Parcelable? = null

    private fun restorePosition() {
        if (mLayoutManagerSavedState != null) {
            layoutManager?.onRestoreInstanceState(mLayoutManagerSavedState)
            mLayoutManagerSavedState = null
        }
    }

    public override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable("SAVED_SUPER_STATE", super.onSaveInstanceState())
        bundle.putParcelable("SAVED_LAYOUT_MANAGER", this.layoutManager!!.onSaveInstanceState())
        return bundle
    }

    public override fun onRestoreInstanceState(s: Parcelable?) {
        var state: Parcelable? = s
        if (state is Bundle) {
            val bundle = state
            mLayoutManagerSavedState = bundle.getParcelable("SAVED_LAYOUT_MANAGER")
            state = bundle.getParcelable("SAVED_SUPER_STATE")
        }
        super.onRestoreInstanceState(state)
    }

    private var lastXScroll: Int = 0

    private val loadStateAdapter: DataBindingLoadStateAdapter
            by lazy { DataBindingLoadStateAdapter() }

    private var snapType: SnapType = SnapType.NONE

    private var lastX = 0.0f
    private var lastY = 0.0f
    private var scrolling = false

    private val pagerSnapHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    var isComplexMode: Boolean = true

    /*fun addMore(list: List<T>, isEnd: Boolean = false) {
        if (adapter == null) {
            adapter = ConcatAdapter(

                bindingAdapter,
                loadStateAdapter
            )
        }

        if (bindingAdapter.itemCount == 0) {
            bindingAdapter.submitList(list)
        } else {
            bindingAdapter.list.addAll(list)
            bindingAdapter.notifyItemRangeInserted(bindingAdapter.itemCount, list.size)
        }

        loadStateAdapter.loadState = LoadState.NotLoading(isEnd)

        adapter = bindingAdapter
    }*/

    private fun getConcatAdapter(): ConcatAdapter {
        return ConcatAdapter()
    }

    private fun saveScrollState(recyclerView: RecyclerView) {
        recyclerView.layoutManager?.let { lm ->
            lm.onSaveInstanceState()?.let {

            }
        }
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SolarRecyclerView,
            0, 0
        ).apply {

            try {
                snapType = when(getInteger(R.styleable.SolarRecyclerView_snapType, 0)) {
                    0 -> SnapType.NONE
                    1 -> SnapType.PAGER
                    else -> SnapType.NONE
                }
                setSnapType(snapType)
            } finally {
                recycle()
            }
        }

        init(context)
    }

    private fun setSnapType(type: SnapType) {
        when(type) {
            SnapType.PAGER -> pagerSnapHelper.attachToRecyclerView(this)
            else -> { }
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        if (isComplexMode) {
            if (layoutManager == null) return super.onInterceptTouchEvent(e)

            val lm = layoutManager as LinearLayoutManager

            if (e != null) {
                var allowScroll = true

                when(e.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        lastX = e.x
                        lastY = e.y

                        if (scrolling) {
                            val newEvent = MotionEvent.obtain(e)
                            newEvent.action = MotionEvent.ACTION_UP
                            return super.onInterceptTouchEvent(e)
                        }
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val currentX = e.x
                        val currentY = e.y
                        val dx = abs(currentX - lastX)
                        val dy = abs(currentY - lastY)
                        allowScroll =
                            if (dy > dx) lm.canScrollVertically() else lm.canScrollHorizontally()
                    }
                }

                if (!allowScroll) {
                    return false
                }
            }
        }

        return super.onInterceptTouchEvent(e)
    }

    private var isAttachablePagerSnap: Boolean = false

    private fun addOnScrollListener() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(VERTICAL) && newState == SCROLL_STATE_IDLE) {
                    //recyclerView.smoothScrollToPosition(bindingAdapter.itemCount)
                }

                when (newState) {
                    SCROLL_STATE_IDLE -> {
                        Log.d(TAG, "ScrollX $${recyclerView.scrollX}")

                        lastXScroll = recyclerView.scrollX

                        saveScrollState(recyclerView)
                    }
                    SCROLL_STATE_DRAGGING -> { }
                    SCROLL_STATE_SETTLING -> { }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                layoutManager?.let { lm ->
                    val visibleItemCount = lm.childCount
                    val totalItemCount = lm.itemCount

                    when (snapType) {
                        SnapType.PAGER -> {
                            val snapPosition = pagerSnapHelper.findTargetSnapPosition(lm, dx, dy)
                            Log.d(
                                TAG, "State: ${getScrollStateStr(recyclerView.scrollState)} SnapPosition: $snapPosition"
                            )

                            if (recyclerView.scrollState == SCROLL_STATE_DRAGGING) {
                                if (snapPosition == 0 || snapPosition == totalItemCount - 1) {
                                    isAttachablePagerSnap = false
                                    pagerSnapHelper.attachToRecyclerView(null)
                                } else {
                                    if (!isAttachablePagerSnap) {
                                        pagerSnapHelper.attachToRecyclerView(this@SampleRecyclerView)
                                        isAttachablePagerSnap = true
                                    }
                                }
                            }
                        }
                        else -> { }
                    }

                    val lastVisibleItemPosition = when (lm) {
                        is LinearLayoutManager -> lm.findLastVisibleItemPosition()
                        is GridLayoutManager -> lm.findLastVisibleItemPosition()
                        else -> return
                    }
                }
            }
        })
    }

    private fun getScrollStateStr(state: Int): String {
        return when(state) {
            SCROLL_STATE_IDLE -> "IDLE"
            SCROLL_STATE_DRAGGING -> "DRAGGING"
            SCROLL_STATE_SETTLING -> "SETTLING"
            else -> "NONE"
        }
    }

    private fun init(context: Context) {
        addOnScrollListener()
    }


    companion object {
        const val TAG = "SolarRecyclerView"
    }
}