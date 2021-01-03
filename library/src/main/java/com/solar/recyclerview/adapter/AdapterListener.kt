package com.solar.recyclerview.adapter

import com.solar.recyclerview.adapter.holder.ItemType

interface AdapterListener {
    fun <T: ItemType>addMore(list: List<T>)
}