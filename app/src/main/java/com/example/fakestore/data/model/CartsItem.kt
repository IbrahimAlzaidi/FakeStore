package com.example.fakestore.data.model


import com.google.gson.annotations.SerializedName

data class CartsItem(
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("__v")
    val v: Int?,
    @SerializedName("products")
    val products: List<ProductsItem>
)