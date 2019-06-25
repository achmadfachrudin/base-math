package com.fachrudin.base.presentation.addition

import androidx.databinding.ObservableField
import com.fachrudin.base.core.BaseViewModel

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class AdditionViewModel : BaseViewModel() {
    var bTextA = ObservableField<String>()
    var bTextB = ObservableField<String>()
    var bTextResult = ObservableField<String>()
    var isShowButton = ObservableField(false)

    var valueA = 0
    var valueB = 0
}