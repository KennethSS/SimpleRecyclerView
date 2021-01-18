package com.solar.recyclerviewsample

import android.os.Build
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.solar.recyclerview.SolarRecyclerView
import com.solar.recyclerview.adapter.normal.AbstractDataBindingAdapter
import com.solar.recyclerview.decoration.SolarItemDecoration
import com.solar.recyclerviewsample.adapter.FoodBindingAdapter
import com.solar.recyclerviewsample.adapter.FoodListAdapter
import com.solar.recyclerviewsample.model.food.Food
import com.solar.recyclerviewsample.model.movie.Movie

@BindingAdapter("clipOutline")
fun setClipOutline(iv: AppCompatImageView, clipToOutline: Boolean?) {
    clipToOutline?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv.clipToOutline = clipToOutline
        }
    }
}

@BindingAdapter("url")
fun setImageFromUrl(iv: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(iv)
            .load(url)
            .apply(RequestOptions.centerCropTransform())
            .into(iv)
    }
}


@BindingAdapter("submit")
fun submitRecyclerView(rv: SolarRecyclerView, list: List<Movie>?) {
    list?.let {
        rv.adapter = object: AbstractDataBindingAdapter<Movie>(rv.layoutManager) {}.apply {
            submitList(list)
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }
}

@BindingAdapter("divider")
fun setItemDivider(rv: RecyclerView, space: Int) {
    rv.addItemDecoration(SolarItemDecoration(space))
}

@BindingAdapter("foods")
fun setFoodListItems(rv: RecyclerView, items: List<Food>?) {
    items?.let {
        (rv.adapter as? FoodListAdapter?)?.submitList(items)
        rv.postDelayed({ }, 2000)
    }
}
