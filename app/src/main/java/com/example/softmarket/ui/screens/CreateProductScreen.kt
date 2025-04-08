package com.example.softmarket.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.softmarket.R
import com.example.softmarket.data.remote.ProductRequest
import com.example.softmarket.data.setBilling
import com.example.softmarket.data.setCategory
import com.example.softmarket.data.setPlatform
import com.example.softmarket.ui.components.MyDropBox
import com.example.softmarket.ui.components.MyTextField
import com.example.softmarket.ui.viewmodel.ProductViewModel

@Composable
fun CreateProductScreen(navController: NavController, viewModel: ProductViewModel, paddingValues: PaddingValues) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var provider by remember { mutableStateOf(TextFieldValue("")) }
    var category by remember { mutableStateOf(TextFieldValue("")) }
    var targetPlatforms by remember { mutableStateOf(TextFieldValue("")) }
    var billing by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var logoUrl by remember { mutableStateOf(TextFieldValue("")) }
    var size by remember { mutableStateOf(TextFieldValue("")) }
    var about by remember { mutableStateOf(TextFieldValue("")) }
    var quantity by remember { mutableStateOf(TextFieldValue("")) }
    val scrollState = rememberScrollState()

    // dropdown options
    val categories = listOf("Engineering", "Productivity", "Graphics", "Other")
    val platforms = listOf("Windows", "macOS", "Linux", "Other")
    val billings = listOf("Monthly", "Annually", "Other")

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        MyTextField(value = provider, onValueChange = { provider = it }, label = { Text("Provider") })
        MyDropBox(
            label = "Category",
            options = categories,
            selected = category.text,
            onValueChange = { category = TextFieldValue(it) }
        )

        MyDropBox(
            label = "Target Platforms",
            options = platforms,
            selected = targetPlatforms.text,
            onValueChange = { targetPlatforms = TextFieldValue(it) }
        )

        MyDropBox(
            label = "Billing",
            options = billings,
            selected = billing.text,
            onValueChange = { billing = TextFieldValue(it) }
        )
        MyTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
        MyTextField(value = logoUrl, onValueChange = { logoUrl = it }, label = { Text("Logo URL") })
        MyTextField(value = size, onValueChange = { size = it }, label = { Text("Size") })
        MyTextField(value = about, onValueChange = { about = it }, label = { Text("About") })
        MyTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Number of licences") })

        Row {
            Button(onClick = {
                val newProduct = ProductRequest(
                    title = title.text,
                    color = provider.text,
                    age = setCategory(category.text),
                    integer_one = setPlatform(targetPlatforms.text),
                    integer_two = setBilling(billing.text),
                    price = price.text.toDouble(),
                    url = logoUrl.text,
                    size = size.text.toInt(),
                    description = about.text,
                    integer_three = quantity.text.toInt()
                )
                viewModel.createProduct(newProduct)
                navController.popBackStack()
            }) {
                Text(stringResource(R.string.create))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(stringResource(R.string.cancel))
            }
        }
    }
}
