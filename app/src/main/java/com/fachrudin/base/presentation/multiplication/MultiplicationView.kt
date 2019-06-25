package com.fachrudin.base.presentation.multiplication

import android.text.TextWatcher
import android.view.View
import com.fachrudin.base.core.BaseView

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
interface MultiplicationView : BaseView {
    var textWatcherA: TextWatcher
    var textWatcherB: TextWatcher
    fun onClickCalculate(view: View)
}