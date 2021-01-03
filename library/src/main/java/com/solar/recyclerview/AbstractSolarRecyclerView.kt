package com.solar.recyclerview

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.recyclerview.widget.*
import com.solar.recyclerview.adapter.DataBindingLoadStateAdapter
import com.solar.recyclerview.attr.SnapType
import kotlin.math.abs


abstract class AbstractSolarRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private var snapType: SnapType = SnapType.NONE

    private var lastX = 0.0f
    private var lastY = 0.0f
    private var scrolling = false

    private val pagerSnapHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    var isComplexMode: Boolean = true

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

                }

                when (newState) {
                    SCROLL_STATE_IDLE -> { }
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

                            if (recyclerView.scrollState == SCROLL_STATE_DRAGGING) {
                                if (snapPosition == 0 || snapPosition == totalItemCount - 1) {
                                    isAttachablePagerSnap = false
                                    pagerSnapHelper.attachToRecyclerView(null)
                                } else {
                                    if (!isAttachablePagerSnap) {
                                        pagerSnapHelper.attachToRecyclerView(this@AbstractSolarRecyclerView)
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

    private fun init(context: Context) {
        addOnScrollListener()
    }

    companion object {
        const val TAG = "SolarRecyclerView"
    }
}