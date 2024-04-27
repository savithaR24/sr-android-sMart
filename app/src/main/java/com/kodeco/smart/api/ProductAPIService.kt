package com.kodeco.smart.api

import com.kodeco.smart.models.Product
import com.kodeco.smart.network.adapter.WrappedProductList
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPIService {
    @GET("/products")
    @WrappedProductList
    suspend fun getAllProducts(): Response<List<Product>>
}