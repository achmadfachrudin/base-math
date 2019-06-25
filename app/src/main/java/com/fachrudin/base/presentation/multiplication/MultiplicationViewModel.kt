package com.fachrudin.base.presentation.multiplication

import androidx.databinding.ObservableField
import com.fachrudin.base.core.BaseViewModel

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class MultiplicationViewModel : BaseViewModel() {
    var bTextA = ObservableField<String>()
    var bTextB = ObservableField<String>()
    var bTextResult = ObservableField<String>()
    var isShowButton = ObservableField(false)

    var valueA = 0
    var valueB = 0
    var valueResult = 0
}