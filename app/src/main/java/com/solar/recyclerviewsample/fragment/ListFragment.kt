package com.solar.recyclerviewsample.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.solar.library.binding.fragment.BindingFragment
import com.solar.recyclerviewsample.R
import com.solar.recyclerviewsample.adapter.FoodListAdapter
import com.solar.recyclerviewsample.databinding.FragmentListBinding
import com.solar.recyclerviewsample.model.food.FoodFactory
import com.solar.recyclerviewsample.viewmodel.FoodViewModel
import kotlinx.android.synthetic.*

/**
 *  Created by Kenneth on 12/16/20
 */
class ListFragment : BindingFragment<FragmentListBinding>() {

    override val layoutRes: Int = R.layout.fragment_list

    private val foodViewModel: FoodViewModel by viewModels()

    override fun onViewCreated(bind: FragmentListBinding, savedInstanceState: Bundle?) {
        with(bind) {
            lifecycleOwner = viewLifecycleOwner
            vm = foodViewModel.also { it.fetchList() }
            adapter = FoodListAdapter()
        }

        // Performance Options
        bind.listView.run {
            setHasFixedSize(true)   // 뷰홀더의 크기가 정적인 경우 크기 계산을 다시하지 않게 설정
            //setItemViewCacheSize(4) // onBindViewHolder 를 다시 실행하지 않고 재활용할 ViewCache 크기
        }

        /*bind.listView.addOnScrollListener(LoadMoreScrollListener({
            Log.d("ListFragment", "EndScroll")
            bind.root.postDelayed({
                foodViewModel.getMoreFoodList()
            }, 3000)
        }))*/

        /*bind.root.postDelayed({
            (bind.listView.adapter as FoodListAdapter).submitList(FoodFactory.getFoodSample())
        }, 1000)*/

        (bind.listView.layoutManager as LinearLayoutManager).isItemPrefetchEnabled

        bind.addall.setOnClickListener {
            (bind.listView.adapter as FoodListAdapter).addAll(FoodFactory.getFoodList(1))
        }
    }
}