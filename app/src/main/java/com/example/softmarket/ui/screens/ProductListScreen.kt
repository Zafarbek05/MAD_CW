package com.example.softmarket.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.softmarket.data.Product
import com.example.softmarket.ui.navigation.Screen
import com.example.softmarket.ui.viewmodel.ProductViewModel

@Composable
fun ProductCard(
    product: Product,
    onProductClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onQuantityChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        var quantity by remember { mutableIntStateOf(product.quantity) }
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = product.logoUrl),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Text("Title: ${product.title}")
            Text("Provider: ${product.provider}")
            Text("Price: ${product.price}")

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    if (quantity > 1)
                        onQuantityChange(product.quantity--)
                }) { Text("-") }
                Text(product.quantity.toString())
                IconButton(onClick = {
                    onQuantityChange(product.quantity++)
                }) { Text("+") }
            }

            Row {
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
                IconButton(onClick = onUpdateClick) {
                    Icon(Icons.Filled.Edit, contentDescription = "Update")
                }
            }
        }
    }
}

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel,
    paddingValues: PaddingValues
) {
    val products = viewModel.products
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)) {
        itemsIndexed(products, itemContent = { index, item ->
            ProductCard(
                product = item,
                onProductClick = {
                    navController.navigate(Screen.ProductDetails.createRoute(item.id))
                },
                onDeleteClick = {
                    navController.navigate(Screen.DeleteProduct.createRoute(item.id))
                },
                onUpdateClick = {
                    navController.navigate(Screen.UpdateProduct.createRoute(item.id))
                },
                onQuantityChange = { quantity ->
                    viewModel.updateQuantity(item.id, quantity)
                })
        })
    }
}