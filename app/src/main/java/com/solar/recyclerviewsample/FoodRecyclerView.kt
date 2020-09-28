package com.solar.recyclerviewsample

import android.content.Context
import android.util.AttributeSet
import com.solar.recyclerview.SolarRecyclerView

class FoodRecyclerView : SolarRecyclerView<Food> {

    override val viewModel: FoodViewModel = FoodViewModel()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}