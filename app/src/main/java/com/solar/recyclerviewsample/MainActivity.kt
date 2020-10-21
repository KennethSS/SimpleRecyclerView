package com.solar.recyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.paging.PagedList
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.ItemListener
import com.solar.recyclerview.RecyclerViewPagination
import com.solar.recyclerview.SolarRecyclerView
import com.solar.recyclerview.adapter.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_food_menu.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_item.setOnClickListener {
            main_recycler_view.addAt(10, FoodFactory.getFood())
        }

        //setListAndState(main_basic_recycler_view, FoodFactory.getFoodSample())
        /*main_basic_recycler_view.adapter = DataBindingAdapter<Food>(
            FoodViewModel()
        ).apply {
            submitList(FoodFactory.getFoodSample())
        }*/

        /*main_recycler_view.run {
            setOnItemListener(object: ItemListener<Food> {
                override fun onClick(view: View, item: Food) {
                    Toast.makeText(view.context, item.title, Toast.LENGTH_SHORT).show()
                }

                override fun onClickByPosition(view: View) {
                }
            })
            viewModel.toastEvent.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            })
            submit(FoodFactory.getFoodSample())
        }*/

        main_recycler_view.run {
            addMore(FoodFactory.getFoodSample(), false)
            listener = object: SolarRecyclerView.RecyclerViewListener {
                override fun isEnd() {
                    postDelayed({
                        addMore(FoodFactory.getFoodSample())
                    }, 2000)
                }
            }
        }

        add_btn.setOnClickListener {
            /*FoodFactory.getFoodSample().addAll(foods)
            (main_basic_recycler_view.adapter as DataBindingListAdapter<Food>)
                .submitList(foods + foods2)*/
        }
    }

    private val foodDiffUtil = object: DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem.title == newItem.title
    }

    private fun setList(recyclerView: RecyclerView) {
        recyclerView.adapter = DataBindingAdapter<Food>(FoodViewModel()).apply {

        }
    }

    private fun setListAndState(recyclerView: RecyclerView, list: List<Food>) {
        val adapter = DataBindingLoadStateAdapter()

        val foodAdapter = DataBindingAdapter<Food>(FoodViewModel()).apply {
            submitList(list)
        }

        recyclerView.adapter = ConcatAdapter(
            foodAdapter,
            adapter
        )

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(RecyclerView.VERTICAL) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    adapter.loadState = LoadState.Loading
                    recyclerView.smoothScrollToPosition(foodAdapter.itemCount)
                    recyclerView.postDelayed({
                        foodAdapter.addAll(list)
                        adapter.loadState = LoadState.NotLoading(false)
                    }, 1000)
                }
            }
        })
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