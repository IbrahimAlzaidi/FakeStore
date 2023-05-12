package com.example.fakestore.ui.categoryDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestore.data.model.ProductsItem
import com.example.fakestore.data.remote.RetrofitClient
import com.example.fakestore.data.repository.FakeStoreRepository
import com.example.fakestore.data.repository.FakeStoreRepositoryImpl
import com.example.fakestore.ui.base.BaseViewModel
import com.example.fakestore.util.Event
import com.example.fakestore.util.OnClickListener
import com.example.fakestore.util.State

class DetailsCategoryViewModel() : BaseViewModel(), OnClickListener {
    private val _productsByCategory = MutableLiveData<State<List<ProductsItem>>>()
    val productsByCategory: LiveData<State<List<ProductsItem>>> get() = _productsByCategory

    private val _navigationToProductDetails: MutableLiveData<Event<Int>> = MutableLiveData()
    val navigationToProductDetails: LiveData<Event<Int>> = _navigationToProductDetails

    private val repository: FakeStoreRepository = FakeStoreRepositoryImpl(RetrofitClient.apiService)

    fun fetchProductsByCategory(category: String) {
        bindStateUpdates(
            stateObservable = repository.getProductsByCategory(category),
            onNext = ::onGetProductsByCategorySuccess,
            onError = ::onGetProductsByCategoryError
        )
    }

    private fun onGetProductsByCategorySuccess(state: State<List<ProductsItem>>) {
        _productsByCategory.value = state
    }

    private fun onGetProductsByCategoryError(error: Throwable) {
        _productsByCategory.postValue(State.Failed(error.message.toString()))
    }

    override fun onClickItem(id: Int) {
        _navigationToProductDetails.postValue(Event(id))
    }
}
