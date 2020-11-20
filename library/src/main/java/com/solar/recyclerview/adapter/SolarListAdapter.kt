package com.solar.recyclerview.adapter

import androidx.lifecycle.ViewModel
import com.solar.recyclerview.ItemType
import com.solar.recyclerview.adapter.normal.DataBindingAdapter

/**
 *  Created by Kenneth on 2020/11/20
 */
class SolarListAdapter<T: ItemType>(viewModel: ViewModel?) : DataBindingAdapter<T>(viewModel) {

}