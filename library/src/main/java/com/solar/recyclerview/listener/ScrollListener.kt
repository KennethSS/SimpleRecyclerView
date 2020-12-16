package com.solar.recyclerview.listener

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by Kenneth on 12/16/20
 */
class ScrollListener(
    private val isAttachDestination: () -> Unit,
    private val threshold: Int = 0
) : RecyclerView.OnScrollListener() {

    private var isAttachedEnd = false
    private var previousCount: Int = 0

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (!recyclerView.canScrollVertically(RecyclerView.VERTICAL) &&
            newState == RecyclerView.SCROLL_STATE_IDLE) {
            //recyclerView.smoothScrollToPosition(bindingAdapter.itemCount)
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        recyclerView.layoutManager?.let { lm ->
            val visibleItemCount = lm.childCount
            val totalItemCount = lm.itemCount

            val lastVisibleItemPosition = when (lm) {
                is LinearLayoutManager -> lm.findLastVisibleItemPosition()
                is GridLayoutManager -> lm.findLastVisibleItemPosition()
                else -> return
            }

            Log.d("ScrollListener", "visibleItemCount: $visibleItemCount  " +
                    "lastVisibleItemPosition: $lastVisibleItemPosition  " +
                    "totalItemCount: $totalItemCount")

            if (isAttachedEnd) {
                if (previousCount < totalItemCount) {
                    isAttachedEnd = false
                }
            } else {
                if (visibleItemCount + lastVisibleItemPosition + threshold > totalItemCount) {
                    isAttachedEnd = true
                    previousCount = totalItemCount
                    isAttachDestination()
                }
            }
        }
    }
}