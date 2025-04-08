package com.example.softmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.softmarket.data.getBilling
import com.example.softmarket.data.getCategory
import com.example.softmarket.data.getPlatform
import com.example.softmarket.ui.navigation.Screen
import com.example.softmarket.ui.viewmodel.ProductViewModel

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel,
) {
    val product = viewModel.products.find { it.id == productId }
    product?.let {
        var quantity by remember { mutableIntStateOf(it.quantity) }
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = it.logoUrl),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp).padding(16.dp),
                contentScale = ContentScale.Crop
            )
            Text("Title: ${it.title}")
            Text("Provider: ${it.provider}")
            Text("Category: ${getCategory(it.category)}")
            Text("Target Platform: ${getPlatform(it.targetPlatforms)}")
            Text("Billing: ${getBilling(it.billing)}")
            Text("Price: ${it.price}$")
            Text("Size: ${it.size} MB")
            Text("About: ${it.about}")
            Text("Stock: $quantity")
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    if (quantity > 1) {
                        quantity--
                        viewModel.updateQuantity(it.id, quantity)
                    } }) {
                    Text("-")
                }
                Text(text = quantity.toString())
                IconButton(onClick = {
                    quantity++
                    viewModel.updateQuantity(it.id, quantity)}) {
                    Text("+")
                }
            }
            Row {
                Button(onClick = { navController.navigate(Screen.UpdateProduct.createRoute(it.id)) }) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { navController.navigate(Screen.DeleteProduct.createRoute(it.id)) }) {
                    Text("Delete")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    navController.popBackStack()
                    viewModel.fetchProducts()
                }) {
                    Text("Back to List")
                }
            }
        }
    } ?: Text("Product not found")
}