package com.fachrudin.base.presentation.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fachrudin.base.R
import com.fachrudin.base.core.BaseRecycleViewAdapter
import com.fachrudin.base.core.BaseViewHolder
import com.fachrudin.base.core.GlideApp
import com.fachrudin.base.core.ViewDataBindingOwner
import com.fachrudin.base.databinding.ItemMenuBinding
import com.fachrudin.base.entities.MenuItem
import com.fachrudin.base.presentation.addition.AdditionActivity
import com.fachrudin.base.presentation.fibonacci.FibonacciActivity
import com.fachrudin.base.presentation.multiplication.MultiplicationActivity
import com.fachrudin.base.presentation.primary.PrimaryActivity

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
class MenuListItemAdapter : BaseRecycleViewAdapter<MenuItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MenuItem> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MenuItem>, position: Int) {
        (holder as ViewHolder).bindData(getItem(position))
    }

    class ViewHolder(context: Context, view: View) :
        BaseViewHolder<MenuItem>(context, view),
        MenuListItemView,
        ViewDataBindingOwner<ItemMenuBinding> {

        override lateinit var binding: ItemMenuBinding
        private var viewModel: MenuListItemViewModel? = null
        private var data: MenuItem? = null

        init {
            binding.vm = MenuListItemViewModel()
            binding.view = this
            viewModel = binding.vm
        }

        override fun bindData(data: MenuItem) {
            this.data = data

            data.let {
                viewModel?.bTextTitle?.set(it.title)

                it.imageUrl?.let { imageUrl ->
                    GlideApp.with(context)
                        .load(imageUrl)
                        .thumbnail(0.1f)
                        .placeholder(R.drawable.bg_placeholder)
                        .error(R.drawable.bg_placeholder)
                        .into(binding.imgMenu)

                    viewModel?.bShowImage?.set(true)
                }
            }
        }

        override fun onClickMenu(view: View) {
            data?.let {
                when (it.id) {
                    1 -> { AdditionActivity.startThisActivity(context) }
                    2 -> { MultiplicationActivity.startThisActivity(context) }
                    3 -> { PrimaryActivity.startThisActivity(context) }
                    4 -> { FibonacciActivity.startThisActivity(context) }
                }
            }
        }
    }
}