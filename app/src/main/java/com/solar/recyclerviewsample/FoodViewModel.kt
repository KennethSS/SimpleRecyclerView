package com.solar.recyclerviewsample

import androidx.lifecycle.*
import com.solar.recyclerviewsample.model.food.Food
import com.solar.recyclerviewsample.model.food.FoodFactory
import kotlinx.coroutines.Dispatchers

class FoodViewModel : ViewModel() {
    val toastEvent = MutableLiveData<String>()

    private val _foodList: MutableLiveData<Int> = MutableLiveData()

    val foodList: LiveData<List<Food>> = _foodList.switchMap {
        liveData(Dispatchers.IO) {
            emit(FoodFactory.getFoodSample())
        }
    }

    val foodGridList: LiveData<List<Food>> = liveData(Dispatchers.IO) {
        emit(FoodFactory.getFoodSample())
    }

    fun getMoreFoodList() {
        _foodList.value = 1
    }

    fun onClick(item: Food) {
        toastEvent.postValue(item.title)
    }
}