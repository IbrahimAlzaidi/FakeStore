package com.example.fakestore.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestore.data.model.ProductsItem
import com.example.fakestore.data.remote.RetrofitClient
import com.example.fakestore.data.repository.FakeStoreRepository
import com.example.fakestore.data.repository.FakeStoreRepositoryImpl
import com.example.fakestore.ui.base.BaseViewModel
import com.example.fakestore.util.State


class DetailsViewModel : BaseViewModel() {
    private val repository: FakeStoreRepository = FakeStoreRepositoryImpl(RetrofitClient.apiService)
    private val _product = MutableLiveData<State<ProductsItem>>()
    val product: LiveData<State<ProductsItem>> get() = _product

    val isRefreshing = MutableLiveData<Boolean>()

    private val _productId = MutableLiveData<Int>()
    val productId: LiveData<Int> get() = _productId

    fun setProductId(id: Int) {
        _productId.value = id
    }

    fun getProductById(productId: Int) {
        isRefreshing.value = true
        bindStateUpdates(
            stateObservable = repository.getProduct(productId),
            onNext = ::onGetProductByIdSuccess,
            onError = ::onGetProductByIdError
        )
    }

    fun getProductById() {
        _productId.value?.let { id ->
            isRefreshing.value = true
            bindStateUpdates(
                stateObservable = repository.getProduct(id),
                onNext = ::onGetProductByIdSuccess,
                onError = ::onGetProductByIdError
            )
        }
    }

    private fun onGetProductByIdSuccess(state: State<ProductsItem>) {
        _product.value = state
        isRefreshing.value = false
        Log.d("DetailsViewModel", "onGetProductByIdSuccess: $state")
    }

    private fun onGetProductByIdError(error: Throwable) {
        _product.postValue(State.Failed(error.message.toString()))
        isRefreshing.value = false
        Log.e("DetailsViewModel", "onGetProductByIdError: ${error.message}")
    }
}