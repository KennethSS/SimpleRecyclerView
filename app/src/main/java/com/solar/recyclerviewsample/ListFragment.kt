package com.solar.recyclerviewsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.solar.recyclerview.SolarNormalListView
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

        bind.listView.solarListViewListener = object: SolarNormalListView.SolarListViewListener {
            override fun isAttachDestination() {
                bind.root.postDelayed({
                    bind.listView.addMore(foodViewModel.getFoodList())
                }, 2000)
            }
        }
        return bind.root
    }


}