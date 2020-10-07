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
import kotlinx.android.synthetic.main.item_food_menu.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_basic_recycler_view.adapter = DataBindingAdapter<Food>(
            FoodViewModel()
        ).apply {
            submitList(FoodFactory.getFoodSample())
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
            submit(FoodFactory.getFoodSample())
        }

        add_btn.setOnClickListener {
            /*FoodFactory.getFoodSample().addAll(foods)
            (main_basic_recycler_view.adapter as DataBindingListAdapter<Food>)
                .submitList(foods + foods2)*/
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