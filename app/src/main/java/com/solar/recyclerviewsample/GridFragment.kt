package com.solar.recyclerviewsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.arasthel.spannedgridlayoutmanager.SpanSize
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import com.solar.recyclerview.adapter.SolarListAdapter
import com.solar.recyclerview.adapter.holder.ItemType
import com.solar.recyclerviewsample.databinding.FragmentGridBinding
import com.solar.recyclerviewsample.model.food.FoodFactory

/**
 *  Created by Kenneth on 12/29/20
 */
class GridFragment : Fragment() {
    lateinit var bind : FragmentGridBinding

    private val foodViewModel: FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_grid, container, false)
        bind.lifecycleOwner = viewLifecycleOwner
        bind.vm = foodViewModel

        bind.listView.onAttachEnd = {
            Log.d("GridFragment", "onAttachEnd")
            bind.root.postDelayed({
                bind.listView.loadMore(FoodFactory.getFoodSample(), isLoading = false)
                //bind.listView.loadMore(FoodFactory.getFoodGridSample(), isLoading = false)
            }, 3000)
        }
        return bind.root
    }
}