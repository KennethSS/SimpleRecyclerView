package com.solar.recyclerviewsample

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun setImageResource(iv: AppCompatImageView, @DrawableRes resId: Int?) {
    resId?.let {
        iv.setImageResource(it)
    }
}