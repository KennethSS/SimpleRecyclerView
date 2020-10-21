package com.solar.recyclerviewsample

object FoodFactory {
    fun getFoodSample(): List<Food> {
        return mutableListOf(
            Food("Luttuce combo 1", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_1),
            Food("Courmet combo 2", "VEG BREAKFAST", getPrice(), R.drawable.item_food_2),
            Food("Spaghetti 3", "VEG BREAKFAST", getPrice(), R.drawable.item_food_3),
            Food("Fries Combo 4", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_4),
            Food("Spaghetti combo 5", "VEG BREAKFAST", getPrice(), R.drawable.item_food_5),
            Food("Courmet combo 6", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_6),
            Food("Luttuce combo 7", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_1),
            Food("Courmet combo 8", "VEG BREAKFAST", getPrice(), R.drawable.item_food_2),
            Food("Spaghetti 9", "VEG BREAKFAST", getPrice(), R.drawable.item_food_3),
            Food("Fries Combo 10", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_4),
            Food("Spaghetti combo 11", "VEG BREAKFAST", getPrice(), R.drawable.item_food_5),
            Food("Courmet combo 12", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_6),
            Food("Luttuce combo 13", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_1),
            Food("Courmet combo 14", "VEG BREAKFAST", getPrice(), R.drawable.item_food_2),
            Food("Spaghetti 15", "VEG BREAKFAST", getPrice(), R.drawable.item_food_3),
            Food("Fries Combo 16", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_4),
            Food("Spaghetti combo 17", "VEG BREAKFAST", getPrice(), R.drawable.item_food_5),
            Food("Courmet combo 18", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_6),
            Food("Luttuce combo 19", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_1),
            Food("Courmet combo 20", "VEG BREAKFAST", getPrice(), R.drawable.item_food_2),
            Food("Spaghetti 21", "VEG BREAKFAST", getPrice(), R.drawable.item_food_3),
            Food("Fries Combo 22", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_4),
            Food("Spaghetti combo 23", "VEG BREAKFAST", getPrice(), R.drawable.item_food_5),
            Food("Courmet combo 24", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_6)
        )
    }

    fun getFood(): Food {
        return Food("Courmet combo 12", "NON-VEG BREAKFAST", getPrice(), R.drawable.item_food_6)
    }

    private fun getPrice(): String = (3..20).random().toString()
}