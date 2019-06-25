package com.fachrudin.base.presentation.primary

import androidx.databinding.ObservableField
import com.fachrudin.base.core.BaseViewModel

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class PrimaryViewModel : BaseViewModel() {
    var bTextA = ObservableField<String>()
    var bTextResult = ObservableField<String>()
    var isShowButton = ObservableField(false)
}