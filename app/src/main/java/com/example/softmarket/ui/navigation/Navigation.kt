package com.example.softmarket.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.softmarket.ui.screens.CreateProductScreen
import com.example.softmarket.ui.screens.DeleteProductScreen
import com.example.softmarket.ui.screens.ProductDetailsScreen
import com.example.softmarket.ui.screens.ProductListScreen
import com.example.softmarket.ui.screens.UpdateProductScreen
import com.example.softmarket.ui.viewmodel.ProductViewModel

sealed class Screen(val route: String) {

    data object ProductList : Screen("product_list")

    data object ProductDetails : Screen("product_details/{productId}"){
        fun createRoute(productId: Int) = "product_details/${productId}"
    }

    object CreateProduct : Screen("create_product")

    object UpdateProduct : Screen("update_product/{productId}"){
        fun createRoute(productId: Int) = "update_product/$productId"
    }

    object DeleteProduct : Screen("delete_product/{productId}"){
        fun createRoute(productId: Int) = "delete_product/$productId"
    }
}

@Composable
fun NavigationScreens(
    navController: NavHostController,
    viewModel: ProductViewModel,
    paddingValues: PaddingValues,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductList.route
    ) {
        composable(Screen.ProductList.route){
            ProductListScreen(navController, viewModel, paddingValues)
        }
        composable(Screen.ProductDetails.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
            ProductDetailsScreen(navController, productId, viewModel)
        }
        composable(Screen.CreateProduct.route) {
            CreateProductScreen(navController, viewModel, paddingValues)
        }
        composable(Screen.UpdateProduct.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
            UpdateProductScreen(navController, productId, viewModel, paddingValues)
        }
        composable(Screen.DeleteProduct.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
            DeleteProductScreen(navController, productId, viewModel, paddingValues)
        }
    }
}