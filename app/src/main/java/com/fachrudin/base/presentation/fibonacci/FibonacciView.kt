package com.fachrudin.base.presentation.fibonacci

import android.text.TextWatcher
import android.view.View
import com.fachrudin.base.core.BaseView

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
interface FibonacciView : BaseView {
    var textWatcherA: TextWatcher
    fun onClickCalculate(view: View)
}