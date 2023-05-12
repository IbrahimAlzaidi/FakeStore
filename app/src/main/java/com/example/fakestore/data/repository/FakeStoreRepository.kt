package com.example.fakestore.data.repository

import com.example.fakestore.data.model.CartsItem
import com.example.fakestore.data.model.ProductsItem
import com.example.fakestore.util.State
import io.reactivex.rxjava3.core.Observable

interface FakeStoreRepository {
    fun getAllProducts(): Observable<State<List<ProductsItem>>>
    fun getProduct(productId: Int): Observable<State<ProductsItem>>
    fun getAllCarts(): Observable<State<List<CartsItem>>>
    fun getProductsByCategory(category: String): Observable<State<List<ProductsItem>>>
}