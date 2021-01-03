package com.solar.recyclerviewsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.solar.recyclerview.listener.LoadMoreScrollListener
import com.solar.recyclerviewsample.databinding.FragmentListBinding

/**
 *  Created by Kenneth on 12/16/20
 */
class ListFragment : Fragment() {
    lateinit var bind : FragmentListBinding

    private val foodViewModel: FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        bind.lifecycleOwner = viewLifecycleOwner
        bind.vm = foodViewModel

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

        foodViewModel.getMoreFoodList()
        return bind.root
    }
}