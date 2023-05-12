package com.example.fakestore.data.repository

import com.example.fakestore.data.model.CartsItem
import com.example.fakestore.data.model.ProductsItem
import com.example.fakestore.data.remote.FakeStoreService
import com.example.fakestore.util.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class FakeStoreRepositoryImpl(
    private val fakeStoreService: FakeStoreService
) : FakeStoreRepository {

    override fun getAllProducts(): Observable<State<List<ProductsItem>>> {
        return wrapWithState { fakeStoreService.getAllProducts() }
    }

    override fun getProduct(productId: Int): Observable<State<ProductsItem>> {
        return wrapWithState { fakeStoreService.getProduct(productId) }
    }

    override fun getAllCarts(): Observable<State<List<CartsItem>>> {
        return wrapWithState { fakeStoreService.getAllCarts() }
    }

    override fun getProductsByCategory(category: String): Observable<State<List<ProductsItem>>> {
        return wrapWithState { fakeStoreService.getProductsByCategory(category) }
    }

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Observable<State<T>> {
        return function()
            .map<State<T>> { response ->
                if (response.isSuccessful) {
                    State.Success(response.body()!!)
                } else {
                    State.Failed(response.message())
                }
            }
            .onErrorReturn { State.Failed(it.message ?: "Unknown error") }
            .startWith(Observable.just(State.Loading))
    }
}