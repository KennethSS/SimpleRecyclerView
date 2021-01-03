package com.solar.recyclerviewsample

import com.solar.recyclerview.adapter.holder.ItemType

/**
 *  Created by Kenneth on 12/29/20
 */
data class FoodGrid(
    val title: String,
    val subtitle: String,
    val price: String,
    val img: Int,
    override val layoutRes: Int = R.layout.item_food_grid
) : ItemType