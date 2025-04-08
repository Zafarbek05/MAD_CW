package com.example.softmarket.ui.screens

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.softmarket.data.getBilling
import com.example.softmarket.data.getCategory
import com.example.softmarket.data.getPlatform
import com.example.softmarket.data.setBilling
import com.example.softmarket.data.setCategory
import com.example.softmarket.data.setPlatform
import com.example.softmarket.ui.components.MyDropBox
import com.example.softmarket.ui.components.MyTextField
import com.example.softmarket.ui.viewmodel.ProductViewModel

@Composable
fun UpdateProductScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel,
    paddingValues: PaddingValues
) {
    val product = viewModel.products.find { it.id == productId }
    product?.let {
        var title by remember { mutableStateOf(TextFieldValue(it.title)) }
        var provider by remember { mutableStateOf(TextFieldValue(it.provider)) }
        var category by remember { mutableStateOf(TextFieldValue(getCategory(it.category))) }
        var targetPlatforms by remember { mutableStateOf(TextFieldValue(getPlatform(it.targetPlatforms))) }
        var billing by remember { mutableStateOf(TextFieldValue(getBilling(it.billing))) }
        var price by remember { mutableStateOf(TextFieldValue(it.price.toString())) }
        var logoUrl by remember { mutableStateOf(TextFieldValue(it.logoUrl)) }
        var size by remember { mutableStateOf(TextFieldValue(it.size.toString())) }
        var about by remember { mutableStateOf(TextFieldValue(it.about)) }
        var quantity by remember { mutableStateOf(TextFieldValue(it.quantity.toString())) }

        // dropdown options
        val categories = listOf("Engineering", "Productivity", "Graphics", "Other")
        val platforms = listOf("Windows", "macOS", "Linux", "Other")
        val billings = listOf("Monthly", "Annually", "Other")

        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
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
            MyTextField(value = quantity, onValueChange = {quantity = it}, label = { Text("Stock")})
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
                        about = about.text,
                        quantity = quantity.text.toInt()
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