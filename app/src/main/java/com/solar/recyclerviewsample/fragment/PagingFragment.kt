package com.solar.recyclerviewsample.fragment

import android.os.Bundle
import com.solar.library.binding.fragment.BindingFragment
import com.solar.recyclerviewsample.R
import com.solar.recyclerviewsample.databinding.FragmentPagingBinding

class PagingFragment : BindingFragment<FragmentPagingBinding>() {
    override val layoutRes: Int = R.layout.fragment_paging

    override fun onViewCreated(bind: FragmentPagingBinding, savedInstanceState: Bundle?) {

    }
}