package com.fachrudin.base.presentation.fibonacci

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.fachrudin.base.R
import com.fachrudin.base.core.BaseActivity
import com.fachrudin.base.core.ViewDataBindingOwner
import com.fachrudin.base.databinding.ActivityFibonacciBinding


/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class FibonacciActivity : BaseActivity(),
    FibonacciView,
    ViewDataBindingOwner<ActivityFibonacciBinding> {

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_fibonacci
    }

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, FibonacciActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel: FibonacciViewModel
    override lateinit var binding: ActivityFibonacciBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = FibonacciViewModel()
        viewModel = binding.vm!!

        initUI()
    }

    private fun initUI() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }

        title = getString(R.string.fibonacci_title)
    }

    private fun calculate() {
        // clear result
        viewModel.bTextResult.set(null)

        val input = viewModel.bTextA.get()!!.toInt()

        // set result
//        viewModel.bTextResult.set(listFibonacciWithSequence().take(input).toList().toString())
        viewModel.bTextResult.set(listFibonacci(input))
    }

    /**
     * Method for create fibonacci list
     * @param count Int
     * @return String
     */
    fun listFibonacci(count: Int): String {
        val result = StringBuilder()
        var pass = 0
        var current = 1
        var fibonacci: Int

        result.append("$pass, $current, ")

        for (i in 0 until count - 2) {
            fibonacci = current + pass
            pass = current
            current = fibonacci

            result.append("$fibonacci, ")
        }

        return result.toString().dropLast(2)
    }

    /**
     * Method for generate fibonacci list
     * @return Int
     */
    private fun listFibonacciWithSequence(): Sequence<Int> {
        return generateSequence(
            Pair(0, 1),
            { Pair(it.second, it.first + it.second) }
        ).map { it.first }
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
                        viewModel.isShowButton.set(true)
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

    override fun onClickCalculate(view: View) {
        calculate()
    }
}