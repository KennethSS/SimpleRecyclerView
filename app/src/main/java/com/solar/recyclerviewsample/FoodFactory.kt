package com.solar.recyclerviewsample

import androidx.core.util.toRange
import java.text.DecimalFormat
import kotlin.random.Random

object FoodFactory {
    fun getFoodSample(): List<Food> {
        return mutableListOf(
            Food("Luttuce combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_1),
            Food("Courmet combo", "VEG BREAKFAST", getPrice(), R.drawable.item_food_2),
            Food("Spaghetti", "VEG BREAKFAST", getPrice(), R.drawable.item_food_3),
            Food("Fries Combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_4),
            Food("Spaghetti combo", "VEG BREAKFAST", getPrice(), R.drawable.item_food_5),
            Food("Courmet combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_6),
            Food("Luttuce combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_1),
            Food("Courmet combo", "VEG BREAKFAST", getPrice(), R.drawable.item_food_2),
            Food("Spaghetti", "VEG BREAKFAST", getPrice(), R.drawable.item_food_3),
            Food("Fries Combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_4),
            Food("Spaghetti combo", "VEG BREAKFAST", getPrice(), R.drawable.item_food_5),
            Food("Courmet combo", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_6)
        )
    }

    private fun getPrice(): String = (3..20).random().toString()
}