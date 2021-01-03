package com.solar.recyclerview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SolarItemDecoration(
    private val space: Int,
    private val spanCount: Int = 1
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)

        if (spanCount > 1) {
            if (position % spanCount == 0) {
                outRect.left = space
                outRect.right = space / 2
            } else if (position % spanCount == spanCount - 1){
                outRect.left = space / 2
                outRect.right = space
            } else {
                outRect.left = space / 2
                outRect.right = space / 2
            }

            // 가장 아래 패딩 값
            if (position == state.itemCount - 1) {
                outRect.bottom = space
            } else {
                outRect.bottom = space
            }
        } else {
            println("childcount ${parent.childCount}")

            parent.adapter?.let { adapter ->
                if (position != adapter.itemCount - 1) {
                    outRect.right = space
                }
            }
        }
    }


}