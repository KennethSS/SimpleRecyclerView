package com.solar.recyclerviewsample

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:src")
fun setImageResource(iv: AppCompatImageView, @DrawableRes resId: Int?) {
    resId?.let {
        iv.setImageResource(it)
    }
}

@BindingAdapter("srcImgRes")
fun setImageSrcResource(iv: AppCompatImageView, @DrawableRes resId: Int?) {
    resId?.let {
        Glide.with(iv)
            .load(resId)
            .into(iv)
    }
}