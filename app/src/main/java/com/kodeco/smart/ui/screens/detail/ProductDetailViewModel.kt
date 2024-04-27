package com.kodeco.smart.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.smart.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductDetailState> =
        MutableStateFlow(ProductDetailState.Loading)
    val uiState: StateFlow<ProductDetailState> = _uiState.asStateFlow()

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            _uiState.value = ProductDetailState.Loading

            _uiState.value = repository.getProduct(productId)?.let { product ->
                ProductDetailState.Success(product)
            } ?: ProductDetailState.Error(Exception("Product not found"))
        }
    }
}