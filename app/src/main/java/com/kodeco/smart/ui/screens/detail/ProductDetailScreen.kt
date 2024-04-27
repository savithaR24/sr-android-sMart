package com.kodeco.smart.ui.screens.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.kodeco.smart.ui.components.ProductDetail
import com.kodeco.smart.ui.components.ProductError
import com.kodeco.smart.ui.components.ProductLoading

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductDetailViewModel,
    onBackTap: () -> Unit,
) {
    LaunchedEffect(key1 = "ProductDetailScreen") {
        viewModel.getProduct(productId)
    }

    val detailsState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = (detailsState.value as? ProductDetailState.Success)?.product?.productTitle.orEmpty(),
                        fontSize = 13.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackTap()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        when (val currentState = detailsState.value) {
            is ProductDetailState.Loading -> ProductLoading()
            is ProductDetailState.Success -> ProductDetail(
                modifier = Modifier.padding(padding),
                currentState.product,
            )

            is ProductDetailState.Error -> ProductError(
                currentState.error,
                onTryAgain = {
                    viewModel.getProduct(productId)
                })
        }
    }
}
