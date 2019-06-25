package com.fachrudin.base.presentation.main

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.fachrudin.base.core.BaseViewModel
import com.fachrudin.base.core.NetworkState
import com.fachrudin.base.entities.Menu
import com.fachrudin.base.network.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class MainPageViewModel(context: Context?) : BaseViewModel() {

    private val service =
        if (context == null) RetrofitFactory.makeRetrofitServiceForTest()
        else RetrofitFactory.makeRetrofitService(context)

    val showLoadingView = ObservableField<Boolean>(true)

    var menu: MutableLiveData<Menu> = MutableLiveData()

    fun getMenuFromApi() {
        networkState.value = NetworkState.LOADING

        GlobalScope.launch(Dispatchers.Main)
        {
            val request = service.getMenuAsync()
            try {
                val response = request.await()

                menu.value = response.body()!!

                if (response.body()!!.menu.isEmpty()) {
                    networkState.value = NetworkState.EMPTY
                } else {
                    networkState.value = NetworkState.LOADED
                }
            } catch (e: Exception) {
                networkState.value = NetworkState.error(e)
            }
        }
    }
}