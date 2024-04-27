package com.kodeco.smart.repositories

import com.kodeco.smart.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val products: Flow<List<Product>>
    suspend fun fetchProducts()
    fun getProduct(index: Int): Product?
    suspend fun favorite(product: Product)
}