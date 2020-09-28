package com.solar.recyclerviewsample

import com.solar.recyclerview.ItemType

data class Food (
    val title: String,
    val subtitle: String,
    val img: Int,
    override val layoutRes: Int = R.layout.item_food_menu
) : ItemType