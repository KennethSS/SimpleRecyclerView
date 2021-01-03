package com.solar.recyclerviewsample.complex

import android.os.Parcelable
import com.solar.recyclerview.adapter.normal.DataBindingAdapter
import com.solar.recyclerview.adapter.holder.BindingHolder
import com.solar.recyclerviewsample.databinding.ItemMovieHorizontalBinding

/**
 * Copyright 2020 Kenneth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/
class ComplexListAdapter : DataBindingAdapter<AbstractComplexModel>() {
    private val stateList = hashMapOf<String, Parcelable>()

    override fun onViewRecycled(holder: BindingHolder<AbstractComplexModel>) {
        super.onViewRecycled(holder)
        val bind = holder.binding
        if (bind is ItemMovieHorizontalBinding) {
            bind.movieListView.layoutManager?.onSaveInstanceState()?.let {
                bind.item?.let { item ->
                    stateList[item.title] = it
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BindingHolder<AbstractComplexModel>, position: Int) {
        super.onBindViewHolder(holder, position)
        val bind = holder.binding

        if (bind is ItemMovieHorizontalBinding) {
            bind.item?.let { item ->
                stateList[item.title]?.let {
                    bind.movieListView.layoutManager?.onRestoreInstanceState(it)
                }
            }
        }
    }
}