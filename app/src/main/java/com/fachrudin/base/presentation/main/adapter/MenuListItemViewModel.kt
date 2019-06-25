package com.fachrudin.base.presentation.main.adapter

import androidx.databinding.ObservableField
import com.fachrudin.base.core.BaseViewModel

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class MenuListItemViewModel : BaseViewModel() {
    var bTextTitle = ObservableField<String>()
    var bShowImage = ObservableField(false)
}