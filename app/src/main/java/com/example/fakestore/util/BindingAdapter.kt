package com.example.fakestore.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.fakestore.R
import com.example.fakestore.ui.base.BaseAdapter
import com.example.fakestore.util.Utils.extractFirstTwoWords
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> View.showWhenSuccess(state: State<T>?) {
    if (state is State.Success) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> View.showWhenError(state: State<T>?) {
    if (state is State.Failed) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> View.showWhenLoading(state: State<T>?) {
    if (state is State.Loading) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:imageUrl"])
fun ImageView.loadImage( url: String?) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.progress_animation)
        .error(R.drawable.baseline_image_24)
        .timeout(5000)
        .into(this)
}

@BindingAdapter(value = ["app:items"])
fun <T> RecyclerView.setItems(items: List<T>?) {
    if (items != null) {
        (this.adapter as BaseAdapter<T>?)?.setItems(this, items)
    } else {
        (this.adapter as BaseAdapter<T>?)?.setItems(this, emptyList())
    }
}

@BindingAdapter("app:shortTitle")
fun TextView.setShortTitle(title: String?) {
    this.text = title?.let { extractFirstTwoWords(it) }
}

@BindingAdapter("formattedDate")
fun setFormattedDate(textView: TextView, dateString: String?) {
    if (dateString != null) {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val inputDate = inputFormat.parse(dateString)
        val outputDate = outputFormat.format(inputDate ?: Date())
        textView.text = "Date: $outputDate"
    }
}

@BindingAdapter("app:isRefreshing")
fun SwipeRefreshLayout.bindIsRefreshing(isRefreshing: Boolean?) {
    this.isRefreshing = isRefreshing ?: false
}