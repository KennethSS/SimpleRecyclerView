package com.solar.recyclerviewsample

import android.os.Build
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.solar.recyclerview.ItemType
import com.solar.recyclerview.SolarNormalListView

@BindingAdapter("clipOutline")
fun setClipOutline(iv: AppCompatImageView, clipToOutline: Boolean?) {
    clipToOutline?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv.clipToOutline = clipToOutline
        }
    }
}

@BindingAdapter("submit", "viewModel")
fun SolarNormalListView.submit(list: List<ItemType>?, viewModel: ViewModel?) {
    list?.let {
        submit(list, viewModel)
    }
}