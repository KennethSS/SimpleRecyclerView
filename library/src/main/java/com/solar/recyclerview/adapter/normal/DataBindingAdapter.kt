package com.solar.recyclerview.adapter.normal

import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.holder.BindingHolder
import com.solar.recyclerview.adapter.holder.ItemType

abstract class DataBindingAdapter<T : ItemType>(
    private val layoutManager: RecyclerView.LayoutManager? = null,
    private val viewModel: ViewModel? = null,
    private val callbackLayoutManager: (( (lm: RecyclerView.LayoutManager) -> Unit) -> Unit)? = null
) : BaseAdapter<T, BindingHolder<T>>() {

    private val scrollStates = hashMapOf<Int, Parcelable>()
    private val stateList = hashMapOf<Int, Parcelable>()

    override fun onViewDetachedFromWindow(holder: BindingHolder<T>) {
        super.onViewDetachedFromWindow(holder)
        Log.d(TAG, "onViewDetachedFromWindow")
    }

    override fun onViewRecycled(holder: BindingHolder<T>) {
        super.onViewRecycled(holder)
        layoutManager?.let {
            it.onSaveInstanceState()?.let {
                Log.d(TAG, "onViewRecycled position: ${holder.position}")
                Log.d(TAG, "onViewRecycled layoutPosition: ${holder.layoutPosition}")
                Log.d(TAG, "onViewRecycled bindingAdapterPosition: ${holder.bindingAdapterPosition}")
                //stateList.put(holder.)
            }
        }



        /*callbackLayoutManager?.invoke { lm ->
            lm.onSaveInstanceState()?.let {
                Log.d("DataBindingAdapter", "layoutPosition: ${holder.layoutPosition}")
                stateList.put(holder.layoutPosition, it)
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return BindingHolder(biding)
    }

    override fun onBindViewHolder(holder: BindingHolder<T>, position: Int) {
        if (list.isNotEmpty()) {
            Log.d("DataBindingAdapter", "position: $position")
            holder.bind(list[position], position, viewModel)
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position).layoutRes

    companion object {
        const val TAG = "DataBindingAdapter"
    }
}