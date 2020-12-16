package com.solar.recyclerviewsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

class NormalFragment<D : ViewDataBinding, VM: ViewModel>(private val clazz : Class<VM>) : BaseFragment() {
    override val layoutRes: Int by lazy { arguments?.getInt(KEY_LAYOUT_RES_ID)?:0 }
    lateinit var bind : D

    private lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        bind.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this).get(clazz)
        bind.setVariable(BR.vm, viewModel)
        return bind.root
    }

    companion object {
        private const val KEY_LAYOUT_RES_ID = "res_id"
        fun <D: ViewDataBinding, VM: ViewModel>newInstance(resId: Int, cls: Class<VM>): NormalFragment<D, VM>{
            val args = Bundle().apply {
                putInt(KEY_LAYOUT_RES_ID, resId)
            }

            val fragment = NormalFragment<D,VM>(cls)
            fragment.arguments = args
            return fragment
        }
    }
}