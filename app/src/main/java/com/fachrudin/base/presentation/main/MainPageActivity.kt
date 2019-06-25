package com.fachrudin.base.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.fachrudin.base.R
import com.fachrudin.base.core.BaseActivity
import com.fachrudin.base.core.NetworkState
import com.fachrudin.base.core.ViewDataBindingOwner
import com.fachrudin.base.core.widget.LoadingView
import com.fachrudin.base.databinding.ActivityMainPageBinding
import com.fachrudin.base.presentation.main.adapter.MenuListItemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class MainPageActivity : BaseActivity(),
    MainPageView,
    ViewDataBindingOwner<ActivityMainPageBinding> {

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_main_page
    }

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, MainPageActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel: MainPageViewModel
    override lateinit var binding: ActivityMainPageBinding

    private lateinit var listAdapter: MenuListItemAdapter

    override var retryListener: LoadingView.OnRetryListener
        get() = object : LoadingView.OnRetryListener {
            override fun onRetry() {
                viewModel.getMenuFromApi()
            }
        }
        set(value) {}

    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = MainPageViewModel(this)
        viewModel = binding.vm!!

        initUI()

        viewModel.getMenuFromApi()
        observeNews()
        observeNetworkState()
    }

    private fun initUI() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setHomeButtonEnabled(false)
        }

        title = getString(R.string.main_title)

        listAdapter = MenuListItemAdapter()
        binding.rvList.adapter = listAdapter
    }

    private fun observeNetworkState() {
        observeData(viewModel.getNetworkState()) { networkState ->
            networkState?.let {
                when (it) {
                    NetworkState.LOADING -> {
                        viewModel.showLoadingView.set(true)
                    }
                    NetworkState.LOADED -> {
                        viewModel.showLoadingView.set(false)
                    }
                    NetworkState.EMPTY -> {
                        viewModel.showLoadingView.set(true)
                        binding.loadingView.showEmpty(
                            getString(R.string.empty_title),
                            getString(R.string.empty_msg),
                            null,
                            R.drawable.img_alert,
                            false
                        )
                    }
                    else -> it.exception?.let { e ->
                        viewModel.showLoadingView.set(true)
                        binding.loadingView.showError(
                            e,
                            getString(R.string.error_title),
                            getString(R.string.error_msg)
                        )
                    }
                }
            }
        }
    }

    private fun observeNews() {
        observeData(viewModel.menu) { result ->
            result?.menu?.let {
                listAdapter.setData(it)
            }
        }
    }

    override fun onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed()
            return
        }
        this.doubleBackPressed = true
        Toast.makeText(this, getString(R.string.app_msg_close), Toast.LENGTH_SHORT).show()

        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            doubleBackPressed = false
        }
    }
}