package com.solar.recyclerview

import android.view.View

interface ItemListener<T> {
    fun onClick(view: View, item: T)
    fun onClickByPosition(view: View)
}