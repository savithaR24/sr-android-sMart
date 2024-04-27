package com.kodeco.smart.repositories

import com.kodeco.smart.api.ProductAPIService
import com.kodeco.smart.database.ProductDao
import com.kodeco.smart.models.Product
import com.kodeco.smart.prefs.ProductPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductRepositoryImpl(
    private val service: ProductAPIService,
    private val productDao: ProductDao,
    private val productPrefs: ProductPrefs,
) : ProductRepository {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var isLocalStorageEnabled: Boolean = false
    private var isFavoritesFeatureEnabled: Boolean = false

    private val _products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    override val products: StateFlow<List<Product>> = _products.asStateFlow()

    init {
        runBlocking() {
            isLocalStorageEnabled = productPrefs.getLocalStorageEnabled().first()
            isFavoritesFeatureEnabled = productPrefs.getFavoritesFeatureEnabled().first()
        }

        coroutineScope.launch {
            productPrefs.getLocalStorageEnabled()
                .collect { localStorageEnabled ->
                    isLocalStorageEnabled = localStorageEnabled
                }
        }

        coroutineScope.launch {
            productPrefs.getFavoritesFeatureEnabled()
                .collect { favoritesFeatureEnabled ->
                    isFavoritesFeatureEnabled = favoritesFeatureEnabled
                }
        }
    }

    override suspend fun fetchProducts() {
        val favorites = if (isFavoritesFeatureEnabled) {
            if (isLocalStorageEnabled) {
                productDao.getFavoriteProducts()
            } else {
                _products.value.filter { it.isFavorite }
            }
        } else {
            emptyList()
        }

        try {
            val productsResponse = service.getAllProducts()

            if (isLocalStorageEnabled) productDao.deleteAllProducts()

            _products.value = emptyList()
            _products.value = if (productsResponse.isSuccessful) {
                val products = productsResponse.body()!!
                    .toMutableList()
                    .map { product ->
                        product.copy(isFavorite = favorites.any { it.productTitle == product.productTitle })
                    }
                if (isLocalStorageEnabled) productDao.addProduct(*products.toTypedArray())
                products
            } else {
                throw Exception("Request failed: ${productsResponse.errorBody()}")
            }
        } catch (e: Exception) {
            if (isLocalStorageEnabled) {
                _products.value = emptyList()
                _products.value = productDao.getAllProducts()
            } else {
                throw Exception("Request failed: ${e.message}")
            }
        }
    }

    override fun getProduct(index: Int): Product? =
        _products.value.getOrNull(index)

    override suspend fun favorite(product: Product) {
        if (isFavoritesFeatureEnabled.not()) return

        val index = _products.value.indexOf(product)
        val mutableProducts = _products.value.toMutableList()
        val updatedProduct = product.copy(isFavorite = product.isFavorite.not())
        mutableProducts[index] = updatedProduct

        if (isLocalStorageEnabled) productDao.updateProduct(updatedProduct)

        _products.value = mutableProducts.toList()
    }
}