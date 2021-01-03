package com.solar.recyclerview.listener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.RecyclerViewController

/**
 *  Created by Kenneth on 12/16/20
 */
class LoadMoreScrollListener(
    private val controller: RecyclerViewController,
    private val onAttachDestination: () -> Unit,
    private val threshold: Int = 0 // todo GridLayout 대응
) : RecyclerView.OnScrollListener() {

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

            if (controller.isAttachedThreshold) {
                if (previousCount < totalItemCount) {
                    controller.isAttachedThreshold = false
                }
            } else {
                if (visibleItemCount + lastVisibleItemPosition + threshold > totalItemCount) {
                    controller.isAttachedThreshold = true
                    previousCount = totalItemCount
                    onAttachDestination()
                }
            }
        }
    }
}