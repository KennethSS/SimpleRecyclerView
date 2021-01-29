package com.solar.recyclerviewsample.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.solar.library.binding.fragment.BindingFragment
import com.solar.recyclerviewsample.R
import com.solar.recyclerviewsample.adapter.ComplexListAdapter
import com.solar.recyclerviewsample.complex.ComplexFactory
import com.solar.recyclerviewsample.complex.ComplexViewModel
import com.solar.recyclerviewsample.databinding.FragmentComplexBinding
import com.solar.recyclerviewsample.viewmodel.FoodViewModel
import kotlinx.coroutines.delay

/**
 * Copyright 2020 Kenneth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **/
class ComplexFragment : BindingFragment<FragmentComplexBinding>() {

    private val complexViewModel: ComplexViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_complex

    override fun onViewCreated(bind: FragmentComplexBinding, savedInstanceState: Bundle?) {
        with(bind) {
            solarRecyclerView.adapter = ComplexListAdapter(complexViewModel, viewLifecycleOwner)

            List(5) {
                bind.root.postDelayed({
                    complexViewModel.getList()
                }, 1000 * it.toLong())
            }
        }
    }
}