package com.solar.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.ViewModel
import com.solar.recyclerview.adapter.holder.ItemType
import com.solar.recyclerview.adapter.normal.AbstractLoadingAdapter

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
class SolarRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AbstractSolarRecyclerView(context, attrs, defStyleAttr) {

    private var controller: RecyclerViewController? = null

    private val onAttachDestination: (() -> Unit) = {
        onAttachEnd?.invoke()
    }

    var onAttachEnd: (() -> Unit)? = null

    init {
        overScrollMode = OVER_SCROLL_NEVER
        clipToPadding = false
    }

    fun <T : ItemType> loadMore(
        list: List<T>,
        viewModel: ViewModel? = null,
        isLoading: Boolean
    ) {
        post {
            if (adapter != null) {
                (adapter as AbstractLoadingAdapter<ItemType>).addAll(list)
            } else {
                /*val loadingAdapter = object: AbstractLoadingAdapter<ItemType>(){ }.apply {
                    submitList(list)
                }

                adapter = loadingAdapter
                controller = RecyclerViewController(
                    this,
                    loadingAdapter,
                    onAttachDestination = {

                    },
                    isLoading = isLoading)*/
            }
        }
    }
}