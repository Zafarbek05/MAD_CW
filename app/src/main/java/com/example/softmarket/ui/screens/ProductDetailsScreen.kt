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
    viewModel: ProductViewModel
) {
    val product = viewModel.products.find { it.id.toInt() == productId }
    product?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = it.logoUrl),
                contentDescription = "Logo",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Crop
            )
            Text("Title: ${it.title}")
            Text("Provider: ${it.provider}")
            Text("Category: ${getCategory(it.category.toInt())}")
            Text("Target Platform: ${getPlatform(it.targetPlatforms.toInt())}")
            Text("Billing: ${getBilling(it.billing.toInt())}")
            Text("Price: ${it.price}$")
            Text("Size: ${it.size} MB$")
            Text("About: ${it.about} MB$")
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { if (it.quantity > 1) it.quantity-- }) {
                    Text("-")
                }
                Text(text = it.quantity.toString())
                IconButton(onClick = { it.quantity }) {
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
            }
        }
    } ?: Text("Product not found")
}