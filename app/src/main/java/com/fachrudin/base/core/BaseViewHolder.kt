package com.fachrudin.base.core

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fachrudin.base.BR

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
abstract class BaseViewHolder<T>(val context: Context, view: View) : RecyclerView.ViewHolder(view) {

    init {
        if (this is ViewDataBindingOwner<*>) {
            setViewBinding(view)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, viewModel)
                binding.executePendingBindings()
            }
//            if (this is BindingViewHolder<*>) {
//                binding.setVariable(BR.view, this)
//            }
        }
    }

    abstract fun bindData(data: T)
}
