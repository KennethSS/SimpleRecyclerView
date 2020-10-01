package com.solar.recyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.solar.recyclerview.ItemListener
import com.solar.recyclerview.RecyclerViewPagination
import com.solar.recyclerview.adapter.DataBindingAdapter
import com.solar.recyclerview.adapter.DataBindingListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foods = mutableListOf(
            Food("Luttuce combo", "NON-VEG BREAKFAST", R.drawable.item_food_1),
            Food("Courmet combo", "VEG BREAKFAST", R.drawable.item_food_2),
            Food("Spaghetti", "VEG BREAKFAST", R.drawable.item_food_3),
            Food("Fries Combo", "NON-VEG BREAKFAST", R.drawable.item_food_4),
            Food("Spaghetti combo", "VEG BREAKFAST", R.drawable.item_food_5),
            Food("Courmet combo", "NON-VEG BREAKFAST", R.drawable.item_food_6),
            Food("Luttuce combo", "NON-VEG BREAKFAST", R.drawable.item_food_1),
            Food("Courmet combo", "VEG BREAKFAST", R.drawable.item_food_2),
            Food("Spaghetti", "VEG BREAKFAST", R.drawable.item_food_3),
            Food("Fries Combo", "NON-VEG BREAKFAST", R.drawable.item_food_4),
            Food("Spaghetti combo", "VEG BREAKFAST", R.drawable.item_food_5),
            Food("Courmet combo", "NON-VEG BREAKFAST", R.drawable.item_food_6)
        )

        val foods2 = mutableListOf(
            Food("Luttuce combo", "NON-VEG BREAKFAST", R.drawable.item_food_1),
            Food("Courmet combo", "VEG BREAKFAST", R.drawable.item_food_2),
            Food("Spaghetti", "VEG BREAKFAST", R.drawable.item_food_3),
            Food("Fries Combo", "NON-VEG BREAKFAST", R.drawable.item_food_4),
            Food("Spaghetti combo", "VEG BREAKFAST", R.drawable.item_food_5),
            Food("Courmet combo", "NON-VEG BREAKFAST", R.drawable.item_food_6),
            Food("Luttuce combo", "NON-VEG BREAKFAST", R.drawable.item_food_1),
            Food("Courmet combo", "VEG BREAKFAST", R.drawable.item_food_2),
            Food("Spaghetti", "VEG BREAKFAST", R.drawable.item_food_3),
            Food("Fries Combo", "NON-VEG BREAKFAST", R.drawable.item_food_4),
            Food("Spaghetti combo", "VEG BREAKFAST", R.drawable.item_food_5),
            Food("Courmet combo", "NON-VEG BREAKFAST", R.drawable.item_food_6)
        )


        main_basic_recycler_view.adapter = DataBindingAdapter<Food>(
            FoodViewModel()
        ).apply {
            submitList(foods)
        }

        main_recycler_view.run {
            setOnItemListener(object: ItemListener<Food> {
                override fun onClick(view: View, item: Food) {
                    Toast.makeText(view.context, item.title, Toast.LENGTH_SHORT).show()
                }

                override fun onClickByPosition(view: View) {
                    TODO("Not yet implemented")
                }
            })
            viewModel.toastEvent.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            })
            submit(foods)
        }

        add_btn.setOnClickListener {
            foods.addAll(foods)
            (main_basic_recycler_view.adapter as DataBindingListAdapter<Food>)
                .submitList(foods + foods2)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }
}