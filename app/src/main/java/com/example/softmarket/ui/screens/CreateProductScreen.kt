package com.example.softmarket.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
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
import com.example.softmarket.data.Product
import com.example.softmarket.data.setBilling
import com.example.softmarket.data.setCategory
import com.example.softmarket.data.setPlatform
import com.example.softmarket.ui.viewmodel.ProductViewModel

@Composable
fun CreateProductScreen(navController: NavController, viewModel: ProductViewModel) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var provider by remember { mutableStateOf(TextFieldValue("")) }
    var category by remember { mutableStateOf(TextFieldValue("")) }
    var targetPlatforms by remember { mutableStateOf(TextFieldValue("")) }
    var billing by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var logoUrl by remember { mutableStateOf(TextFieldValue("")) }
    var size by remember { mutableStateOf(TextFieldValue("")) }
    var about by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = provider, onValueChange = { provider = it }, label = { Text("Provider") })
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        TextField(value = targetPlatforms, onValueChange = { targetPlatforms = it }, label = { Text("Target Platforms") })
        TextField(value = billing, onValueChange = { billing = it }, label = { Text("Billing") })
        TextField(value = price, onValueChange = { price = it }, label = { Text("Price") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        TextField(value = logoUrl, onValueChange = { logoUrl = it }, label = { Text("Logo URL") })
        TextField(value = size, onValueChange = { size = it }, label = { Text("Size") })
        TextField(value = about, onValueChange = { about = it }, label = { Text("About") })
        Row {
            Button(onClick = {
                val newProduct = Product(
                    title = title.text,
                    provider = provider.text,
                    category = setCategory(category.text),
                    targetPlatforms = setPlatform(targetPlatforms.text),
                    billing = setBilling(billing.text),
                    price = price.text.toDouble(),
                    logoUrl = logoUrl.text,
                    size = size.text.toInt(),
                    about = about.text
                )
                viewModel.createProduct(newProduct)
                navController.popBackStack()
            }) {
                Text("Add")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Cancel")
            }
        }
    }
}
