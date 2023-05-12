package com.example.fakestore.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestore.ui.base.BaseViewModel
import com.example.fakestore.util.Event
import com.example.fakestore.util.OnCategoryClickListener


class CategoryViewModel: BaseViewModel(), OnCategoryClickListener {
    private val _navigationToDetails: MutableLiveData<Event<String>> = MutableLiveData()
    val navigationToDetails: LiveData<Event<String>> = _navigationToDetails

    override fun onClickCategory(category: String) {
        _navigationToDetails.postValue(Event(category))
    }
}