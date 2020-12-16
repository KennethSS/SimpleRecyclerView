package com.solar.recyclerview

import androidx.lifecycle.ViewModel

/**
 *  Created by Kenneth on 12/16/20
 */
interface RecyclerViewListener {
    fun <T: ItemType>submit(list: List<T>, viewModel: ViewModel? = null)
}