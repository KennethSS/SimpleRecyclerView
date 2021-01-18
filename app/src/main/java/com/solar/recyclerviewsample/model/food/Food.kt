package com.solar.recyclerviewsample.model.food

import com.solar.recyclerviewsample.R
import com.solar.recyclerviewsample.complex.AbstractComplexModel

data class Food (
    var title: String,
    val subtitle: String,
    val price: String,
    val img: Int,
    override val layoutRes: Int = R.layout.item_food_menu
) : AbstractComplexModel()