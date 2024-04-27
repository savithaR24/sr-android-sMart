package com.kodeco.smart.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.kodeco.smart.ui.components.ProductSettingsToggleRow

@Composable
fun ProductSettingsScreen(
    viewModel: ProductSettingsViewModel,
    onBackTap: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackTap()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            ProductSettingsToggleRow(
                label = "Enable Local Storage",
                isToggleChecked = uiState.localStorageEnabled,
                onToggleChanged = {
                    viewModel.toggleLocalStorage()
                },
            )

            ProductSettingsToggleRow(
                label = "Enable Favorites Feature",
                isToggleChecked = uiState.favoritesFeatureEnabled,
                onToggleChanged = {
                    viewModel.toggleFavoritesFeature()
                },
            )
        }
    }
}