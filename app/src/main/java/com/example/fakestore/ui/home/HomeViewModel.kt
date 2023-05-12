package com.example.fakestore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.fakestore.data.model.ProductsItem
import com.example.fakestore.data.remote.RetrofitClient
import com.example.fakestore.data.repository.FakeStoreRepository
import com.example.fakestore.data.repository.FakeStoreRepositoryImpl
import com.example.fakestore.ui.base.BaseViewModel
import com.example.fakestore.util.Event
import com.example.fakestore.util.OnClickListener
import com.example.fakestore.util.State

class HomeViewModel: BaseViewModel(), OnClickListener {

    private val _products = MutableLiveData<State<List<ProductsItem>>>()
    val products: LiveData<State<List<ProductsItem>>> get() = _products

    private val _navigationToProductDetails: MutableLiveData<Event<Int>> = MutableLiveData()
    val navigationToProductDetails: LiveData<Event<Int>> = _navigationToProductDetails

    private val repository: FakeStoreRepository = FakeStoreRepositoryImpl(RetrofitClient.apiService)

    val isRefreshing = MutableLiveData<Boolean>()
    val searchQuery = MutableLiveData<String>()

    private val _filteredProducts = MediatorLiveData<List<ProductsItem>>()
    val filteredProducts: LiveData<List<ProductsItem>> = _filteredProducts

    init {
        getAllProducts()

        _filteredProducts.addSource(_products) { filterProducts() }
        _filteredProducts.addSource(searchQuery) { filterProducts() }
    }

    private fun filterProducts() {
        val query = searchQuery.value.orEmpty()
        var products: List<ProductsItem> = emptyList()

        if (_products.value is State.Success) {
            products = (_products.value as State.Success<List<ProductsItem>>).data
        }

        _filteredProducts.value = if (query.isEmpty()) {
            products
        } else {
            products.filter { it.title?.contains(query, ignoreCase = true) == true }
        }
    }

    fun getAllProducts() {
        isRefreshing.value = true
        bindStateUpdates(
            stateObservable = repository.getAllProducts(),
            onNext = ::onGetProductsSuccess,
            onError = ::onGetProductsError
        )
    }

    private fun onGetProductsSuccess(state: State<List<ProductsItem>>) {
        _products.value = state
        isRefreshing.value = false
    }

    private fun onGetProductsError(error: Throwable) {
        _products.postValue(State.Failed(error.message.toString()))
        isRefreshing.value = false
    }

    override fun onClickItem(id: Int) {
        _navigationToProductDetails.postValue(Event(id))
    }
}