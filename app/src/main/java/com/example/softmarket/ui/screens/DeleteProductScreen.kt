package com.example.softmarket.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.softmarket.ui.viewmodel.ProductViewModel

@Composable
fun DeleteProductScreen(navController: NavController, productId: Int, viewModel: ProductViewModel) {
    val product = viewModel.products.find { it.id.toInt() == productId }
    product?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Are you sure you want to delete ${it.title}?")
            Row {
                Button(onClick = { viewModel.deleteProduct(it.id.toInt()); navController.popBackStack() }) {
                    Text("Yes")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("No")
                }
            }
        }
    }
}