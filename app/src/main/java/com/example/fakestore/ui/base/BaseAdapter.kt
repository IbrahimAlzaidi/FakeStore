package com.example.fakestore.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fakestore.util.DiffUtilAdapter
import com.example.fakestore.util.OnClickListener
import com.example.fakestore.BR

abstract class BaseAdapter<T>(
    private var items: List<T>,
    private val listener: OnClickListener?,
    private val layoutId: Int,
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val current = items[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.item, current)
                holder.binding.setVariable(BR.listener, listener)

            }
        }
    }

    override fun getItemViewType(position: Int) = position

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(view: RecyclerView,newItems: List<T> , position: Int? = null) {
        val diffUtilResult = DiffUtil.calculateDiff(DiffUtilAdapter(items, newItems))
        items = newItems
        diffUtilResult.dispatchUpdatesTo(this)
        if (position != null){
            view.smoothScrollToPosition(position)
        }
    }

    override fun getItemCount() = items.size

    fun getItems() = items

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(bindings: ViewDataBinding) :
        RecyclerView.ViewHolder(bindings.root)
}