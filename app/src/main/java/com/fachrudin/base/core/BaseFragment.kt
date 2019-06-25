package com.fachrudin.base.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fachrudin.base.BR

/**
 * @author achmad.fachrudin
 * @date 3-Mar-19
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return getLayoutIfDefined(inflater, container)
    }

    private fun getLayoutIfDefined(inflater: LayoutInflater, container: ViewGroup?): View? {
        val layoutResId = getViewLayoutResId()
        if (layoutResId == View.NO_ID) return null

        if (this is ViewDataBindingOwner<*>) {
            val view = inflateContentViewBinding(inflater, container, layoutResId)
            if (this is ViewModelOwner<*>) {
                binding.setVariable(BR.vm, this.viewModel)
            }
            if (this is BaseView) {
                binding.setVariable(BR.view, this)
            }
            return view
        } else {
            return inflater.inflate(layoutResId, container, false)
        }
    }

    open protected fun getViewLayoutResId(): Int {
        val layout = javaClass.annotations.find { it is ViewLayout } as? ViewLayout
        return layout?.value ?: View.NO_ID
    }
}