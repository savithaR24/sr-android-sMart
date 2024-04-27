package com.kodeco.smart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kodeco.smart.models.Product
import com.kodeco.smart.ui.screens.list.ProductListState

@Composable
fun ProductList(
    productListState: ProductListState.Success,
    onRefreshTap: () -> Unit,
    onProductTap: (index: Int) -> Unit,
    onProductFavorite: (product: Product) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                onRefreshTap()
            }) {
                Text(text = "Refresh")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            itemsIndexed(productListState.products) { index, product ->
                ProductListRow(
                    product = product,
                    isFavoritesFeatureEnabled = productListState.isFavoritesFeatureEnabled,
                    onClick = {
                        onProductTap(index)
                    },
                    onFavorite = {
                        onProductFavorite(product)
                    },
                )
            }
        }
    }
}
