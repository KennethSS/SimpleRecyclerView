package com.solar.recyclerviewsample

import android.content.Context
import android.util.AttributeSet
import com.solar.recyclerview.SolarRecyclerView

class FoodRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SolarRecyclerView<Food>(context, attrs, defStyleAttr)