package com.solar.recyclerviewsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.*
import com.solar.recyclerview.adapter.normal.DataBindingAdapter
import com.solar.recyclerviewsample.complex.ComplexFactory
import com.solar.recyclerviewsample.complex.ComplexFragment
import com.solar.recyclerviewsample.databinding.FragmentListBinding
import com.solar.recyclerviewsample.model.food.Food
import com.solar.recyclerviewsample.model.food.FoodFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val foodViewModel: FoodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_pager.offscreenPageLimit = 0
        main_pager.adapter = object: FragmentPagerAdapter(supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment = when(position) {
                0 -> GridFragment()
                1 -> ComplexFragment()
                2 -> ListFragment()
                3 -> NormalFragment.newInstance<FragmentListBinding, FoodViewModel>(R.layout.fragment_list, FoodViewModel::class.java)
                else -> NormalFragment.newInstance<FragmentListBinding, FoodViewModel>(R.layout.fragment_list, FoodViewModel::class.java)
            }
            override fun getCount(): Int = 1
        }

        /*main_basic_recycler_view.also {
            it.itemAnimator = object: DefaultItemAnimator() {
                override fun onAnimationFinished(viewHolder: RecyclerView.ViewHolder) {
                    super.onAnimationFinished(viewHolder)
                    Log.d("MainActivity", "onAnimationFinished")
                }
            }
            it.adapter = adapter
            it.addOnScrollListener(ScrollListener(4) {
                Log.d("MainActivity", "isAttachDestination")
            })
        }*/

        //setListAndState(main_basic_recycler_view, FoodFactory.getFoodSample())


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

        /*main_recycler_view.run {
            addMore(FoodFactory.getFoodSample())

            listener = object: SolarRecyclerView.RecyclerViewListener {
                override fun isEnd() {
                    postDelayed({
                        addMore(FoodFactory.getFoodSample(), true)
                    }, 2000)
                }
            }
        }*/

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
        recyclerView.adapter = object: DataBindingAdapter<Food>(
            viewModel = FoodViewModel()
        ){}.apply {

        }
    }

    private fun setListAndState(recyclerView: RecyclerView, list: List<Food>) {
        val adapter = DataBindingLoadStateAdapter()

        val foodAdapter = object: DataBindingAdapter<Food>(
            viewModel = FoodViewModel()
        ){}.apply {
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