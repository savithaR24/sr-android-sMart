package com.kodeco.smart.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.smart.ui.screens.about.AppAboutScreen
import com.kodeco.smart.ui.screens.detail.ProductDetailScreen
import com.kodeco.smart.ui.screens.list.ProductListScreen
import com.kodeco.smart.ui.screens.settings.ProductSettingsScreen

@Composable
fun ProductInfoNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationItem.List.route
    ) {
        composable(
            NavigationItem.List.route
        ) {
            ProductListScreen(
                viewModel = hiltViewModel(),
                onProductRowTap = {
                    navController.navigate("${NavigationItem.Details.route}/$it")
                },
                onSettingsTap = {
                    navController.navigate(NavigationItem.Settings.route)
                },
                onAboutTap = {
                    navController.navigate(NavigationItem.About.route)
                }
            )
        }

        composable(
            "${NavigationItem.Details.route}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType }),
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("productId")
            id?.let {
                ProductDetailScreen(
                    productId = id,
                    viewModel = hiltViewModel(),
                    onBackTap = {
                        navController.navigate(NavigationItem.List.route)
                    }
                )
            }
        }

        composable(
            NavigationItem.About.route
        ) {
            AppAboutScreen(
                onBackTap = {
                    navController.navigate(NavigationItem.List.route)
                }
            )
        }

        composable(
            NavigationItem.Settings.route
        ) {
            ProductSettingsScreen(
                viewModel = hiltViewModel(),
                onBackTap = {
                    navController.navigate(NavigationItem.List.route)
                }
            )
        }
    }
}