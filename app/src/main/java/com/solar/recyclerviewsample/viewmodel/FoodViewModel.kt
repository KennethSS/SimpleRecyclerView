package com.solar.recyclerviewsample.viewmodel

import androidx.lifecycle.*
import com.solar.recyclerview.ViewModelList
import com.solar.recyclerviewsample.model.food.Food
import com.solar.recyclerviewsample.model.food.FoodFactory
import kotlinx.coroutines.Dispatchers

class FoodViewModel : ViewModel(), ViewModelList {
    val toastEvent = MutableLiveData<String>()

    private val _foodList: MutableLiveData<Int> = MutableLiveData()

    val foodList: LiveData<List<Food>> = _foodList.switchMap {
        liveData(Dispatchers.IO) {
            emit(FoodFactory.getFoodList(5))
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

    override fun fetchList() {
        _foodList.value = 1
    }
}