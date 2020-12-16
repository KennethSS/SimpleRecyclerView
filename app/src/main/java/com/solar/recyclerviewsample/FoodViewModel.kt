package com.solar.recyclerviewsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {
    val toastEvent = MutableLiveData<String>()

    fun onClick(item: Food) {
        toastEvent.postValue(item.title)
    }


    fun getFoodList(): List<Food> {
        return FoodFactory.getFoodSample()
    }
}