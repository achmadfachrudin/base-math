package com.fachrudin.base.presentation.multiplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.fachrudin.base.R
import com.fachrudin.base.core.BaseActivity
import com.fachrudin.base.core.ViewDataBindingOwner
import com.fachrudin.base.databinding.ActivityMultiplicationBinding

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class MultiplicationActivity : BaseActivity(),
    MultiplicationView,
    ViewDataBindingOwner<ActivityMultiplicationBinding> {

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_multiplication
    }

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, MultiplicationActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel: MultiplicationViewModel
    override lateinit var binding: ActivityMultiplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = MultiplicationViewModel()
        viewModel = binding.vm!!

        initUI()
    }

    private fun initUI() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        title = getString(R.string.multiplication_title)
    }

    private fun calculate() {
        // clear result
        viewModel.bTextResult.set(null)

        // set result
        viewModel.bTextResult.set(resultMultiplication(viewModel.valueA, viewModel.valueB).toString())
    }

    /**
     * Method for calculate multiplication
     * @param valueA Int
     * @param valueB Int
     * @return Int
     */
    fun resultMultiplication(valueA: Int, valueB: Int): Int {
        return valueA * valueB
    }

    override var textWatcherA: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            binding.edtA.removeTextChangedListener(this)

            try {
                val originalString = s.toString()

                if (originalString.isNotEmpty()) {
                    if (originalString[0] == '0') {
                        viewModel.bTextA.set(null)
                    } else {
                        viewModel.valueA = originalString.toInt()
                        viewModel.isShowButton.set(viewModel.bTextB.get()?.isNotEmpty())
                    }
                } else {
                    viewModel.isShowButton.set(false)
                }
            } catch (e: Exception) {
                if (s.isNullOrEmpty()) {
                    viewModel.bTextA.set(null)
                }
            }

            binding.edtA.addTextChangedListener(this)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // ignore
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // ignore
        }
    }

    override var textWatcherB: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            binding.edtB.removeTextChangedListener(this)

            try {
                val originalString = s.toString()

                if (originalString.isNotEmpty()) {
                    if (originalString[0] == '0') {
                        viewModel.bTextB.set(null)
                    } else {
                        viewModel.valueB = originalString.toInt()
                        viewModel.isShowButton.set(viewModel.bTextA.get()?.isNotEmpty())
                    }
                } else {
                    viewModel.isShowButton.set(false)
                }
            } catch (e: Exception) {
                if (s.isNullOrEmpty()) {
                    viewModel.bTextB.set(null)
                }
            }

            binding.edtB.addTextChangedListener(this)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // ignore
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // ignore
        }
    }

    override fun onClickCalculate(view: View) {
        calculate()
    }
}