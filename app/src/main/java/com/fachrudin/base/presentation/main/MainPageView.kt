package com.fachrudin.base.presentation.main

import com.fachrudin.base.core.BaseView
import com.fachrudin.base.core.widget.LoadingView

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
interface MainPageView : BaseView {
    var retryListener: LoadingView.OnRetryListener
}