package com.example.softmarket.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.softmarket.data.getCategory
import com.example.softmarket.data.setBilling
import com.example.softmarket.data.setCategory
import com.example.softmarket.data.setPlatform
import com.example.softmarket.ui.viewmodel.ProductViewModel

@Composable
fun UpdateProductScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel
) {
    val product = viewModel.products.find { it.id == productId }
    product?.let {
        var title by remember { mutableStateOf(TextFieldValue(it.title)) }
        var provider by remember { mutableStateOf(TextFieldValue(it.provider)) }
        var category by remember { mutableStateOf(TextFieldValue(getCategory(it.category))) }
        var targetPlatforms by remember { mutableStateOf(TextFieldValue(getCategory(it.targetPlatforms))) }
        var billing by remember { mutableStateOf(TextFieldValue(getCategory(it.billing))) }
        var price by remember { mutableStateOf(TextFieldValue(it.price.toString())) }
        var logoUrl by remember { mutableStateOf(TextFieldValue(it.logoUrl)) }
        var size by remember { mutableStateOf(TextFieldValue(it.size.toString())) }
        var about by remember { mutableStateOf(TextFieldValue(it.about)) }

        Column(modifier = Modifier.padding(16.dp)) {
            TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
            TextField(value = provider, onValueChange = { provider = it }, label = { Text("Provider") })
            TextField(value = (category), onValueChange = { category = it }, label = { Text("Category") })
            TextField(value = targetPlatforms, onValueChange = { targetPlatforms = it }, label = { Text("Target Platforms") })
            TextField(value = billing, onValueChange = { billing = it }, label = { Text("Billing") })
            TextField(value = price, onValueChange = { price = it }, label = { Text("Price") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            TextField(value = logoUrl, onValueChange = { logoUrl = it }, label = { Text("Logo URL") })
            TextField(value = size, onValueChange = { size = it }, label = { Text("Size") })
            TextField(value = about, onValueChange = { about = it }, label = { Text("About") })
            Row {
                Button(onClick = {
                    val updatedProduct = it.copy(
                        title = title.text,
                        provider = provider.text,
                        category = setCategory(category.text),
                        targetPlatforms = setPlatform(targetPlatforms.text),
                        billing = setBilling(billing.text),
                        price = price.text.toDoubleOrNull() ?: 0.0,
                        logoUrl = logoUrl.text,
                        size = size.text.toInt(),
                        about = about.text
                    )
                    viewModel.updateProduct(it.id, updatedProduct)
                    navController.popBackStack()
                }) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Cancel")
                }
            }
        }
    }
}