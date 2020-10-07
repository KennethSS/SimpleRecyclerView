package com.solar.recyclerviewsample

import android.os.Build
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("clipOutline")
fun setClipOutline(iv: AppCompatImageView, clipToOutline: Boolean?) {
    clipToOutline?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv.clipToOutline = clipToOutline
        }
    }
}