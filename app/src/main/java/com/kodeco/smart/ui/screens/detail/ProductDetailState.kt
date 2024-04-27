package com.kodeco.smart.ui.screens.detail

import com.kodeco.smart.models.Product

sealed class ProductDetailState {
    data object Loading : ProductDetailState()
    data class Success(val product: Product) : ProductDetailState()
    data class Error(val error: Throwable) : ProductDetailState()
}