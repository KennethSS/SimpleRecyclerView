package com.solar.recyclerview

import androidx.lifecycle.LiveData
import com.solar.recyclerview.adapter.holder.ItemType

interface ViewModelList<T: ItemType> {
    val list: LiveData<ArrayList<T>>
}