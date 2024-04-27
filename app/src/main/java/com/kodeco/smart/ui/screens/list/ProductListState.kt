package com.kodeco.smart.ui.screens.list

import com.kodeco.smart.models.Product

sealed class ProductListState {
    data object Loading : ProductListState()
    data class Success(
        val products: List<Product>,
        val isFavoritesFeatureEnabled: Boolean,
    ) : ProductListState()

    data class Error(val error: Throwable) : ProductListState()
}