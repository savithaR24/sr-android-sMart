package com.kodeco.smart.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.smart.models.Product
import com.kodeco.smart.prefs.ProductPrefs
import com.kodeco.smart.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val prefs: ProductPrefs,
) : ViewModel() {
    private var isFavoritesFeatureEnabled = false
        set(value) {
            field = value
            val uiStateValue = _uiState.value
            if (uiStateValue is ProductListState.Success) {
                _uiState.value = uiStateValue.copy(isFavoritesFeatureEnabled = value)
            }
        }

    private val _uiState: MutableStateFlow<ProductListState> =
        MutableStateFlow(ProductListState.Loading)
    val uiState: StateFlow<ProductListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            prefs.getFavoritesFeatureEnabled()
                .collect {
                    isFavoritesFeatureEnabled = it
                }
        }

        viewModelScope.launch {
            repository
                .products
                .catch {
                    _uiState.value = ProductListState.Error(it)
                }
                .collect {
                    _uiState.value = ProductListState.Success(
                        it,
                        isFavoritesFeatureEnabled = isFavoritesFeatureEnabled,
                    )
                }
        }
        fetchProducts()
    }

    fun fetchProducts() {
        _uiState.value = ProductListState.Loading

        viewModelScope.launch {
            try {
                repository.fetchProducts()
            } catch (e: Exception) {
                _uiState.value = ProductListState.Error(e)
            }
        }
    }

    fun favorite(product: Product) {
        viewModelScope.launch {
            repository.favorite(product)
        }
    }
}