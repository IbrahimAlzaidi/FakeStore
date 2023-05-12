package com.example.fakestore.data.remote

import com.example.fakestore.data.model.CartsItem
import com.example.fakestore.data.model.ProductsItem
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreService {
    @GET("products")
    fun getAllProducts(): Single<Response<List<ProductsItem>>>

    @GET("products/{id}")
    fun getProduct(@Path("id") productId: Int): Single<Response<ProductsItem>>

    @GET("carts")
    fun getAllCarts(): Single<Response<List<CartsItem>>>

    @GET("products/category/{category}")
    fun getProductsByCategory(@Path("category") category: String): Single<Response<List<ProductsItem>>>
}