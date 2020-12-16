package com.solar.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.SolarListAdapter
import com.solar.recyclerview.listener.ScrollListener

/**
 *  Created by Kenneth on 12/16/20
 */
class SolarNormalListView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr), RecyclerViewListener {

    var solarListViewListener: SolarListViewListener? = null

    init {
        addOnScrollListener(ScrollListener(isAttachDestination = {
            solarListViewListener?.isAttachDestination()
        }))
    }

    fun <T: ItemType>addMore(list: List<T>, isEnd: Boolean = false) {
        (adapter as SolarListAdapter<T>).run {
            isLoading = !isEnd
            addAll(list)
        }
    }

    override fun <T : ItemType> submit(list: List<T>, viewModel: ViewModel?) {
        adapter = SolarListAdapter<T>(viewModel).apply {
            submitList(list)
        }
    }

    interface SolarListViewListener {
        fun isAttachDestination()
    }
}