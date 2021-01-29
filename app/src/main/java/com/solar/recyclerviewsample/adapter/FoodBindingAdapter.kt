package com.solar.recyclerviewsample.adapter

import androidx.recyclerview.widget.DiffUtil
import com.solar.recyclerview.adapter.normal.AbstractLoadingAdapter
import com.solar.recyclerviewsample.model.food.Food
import com.solar.recyclerviewsample.viewmodel.FoodViewModel

/**
 *  Created by Kenneth on 12/29/20
 */
class FoodBindingAdapter : AbstractLoadingAdapter<Food>(FoodViewModel()) {

    fun updateList(newList: List<Food>) {
        /*val result = DiffUtil.calculateDiff(FoodDiffUtil(list, newList))
        list.addAll(newList)
        result.dispatchUpdatesTo(this)*/
    }

    inner class FoodDiffUtil(
        private val oldList: List<Food>,
        private val newList: List<Food>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == newList[newItemPosition].title
        }
    }
}

