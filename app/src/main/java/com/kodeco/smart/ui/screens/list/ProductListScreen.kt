package com.kodeco.smart.ui.screens.list

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.kodeco.smart.ui.components.ProductError
import com.kodeco.smart.ui.components.ProductList
import com.kodeco.smart.ui.components.ProductLoading

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    onProductRowTap: (Int) -> Unit,
    onAboutTap: () -> Unit,
    onSettingsTap: () -> Unit,
) {
    val infoState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "SMart")
                },
                actions = {
                    IconButton(
                        onClick = onSettingsTap,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings",
                        )
                    }

                    IconButton(
                        onClick = onAboutTap,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Help,
                            contentDescription = "About",
                        )
                    }
                }
            )
        }
    ) { padding ->
        val transition = updateTransition(
            targetState = infoState,
            label = "list_state_transition",
        )
        transition.Crossfade(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentKey = { it.javaClass },
        ) { state ->
            when (state) {
                is ProductListState.Loading -> ProductLoading()

                is ProductListState.Success -> ProductList(
                    state,
                    onRefreshTap = {
                        viewModel.fetchProducts()
                    },
                    onProductTap = {
                        onProductRowTap(it)
                    },
                    onProductFavorite = {
                        viewModel.favorite(it)
                    }
                )

                is ProductListState.Error -> ProductError(
                    state.error,
                    onTryAgain = {
                        viewModel.fetchProducts()
                    })
            }
        }
    }
}