package com.example.softmarket.ui.viewmodel

import ProductRepository
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.softmarket.data.Product
import com.example.softmarket.data.remote.ProductResponse
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    val products = mutableStateListOf<Product>()
    val product = mutableStateOf<Product?>(null)
    val toast = mutableStateOf<String?>(null)

    init {
        fetchProducts()
    }

    fun fetchProducts(){
        viewModelScope.launch {
            try {
                products.clear()
                products.addAll(repository.getProducts())
            } catch (e: Exception){
                toast.value = "Error fetching products: ${e.message}"
                android.util.Log.d("Fetch Error", "$e" )
            }
        }
    }

    fun createProduct(product: Product){
        viewModelScope.launch {
            try {
                repository.createProduct(product)
                fetchProducts()
                toast.value = "Product ${product.title} created successfully!"
            } catch (e: Exception){
                toast.value = "Error creating a product: ${e.message}"
            }
        }
    }

    fun updateProduct(id: Int, product: Product){
        viewModelScope.launch {
            try {
                repository.updateProduct(id, product)
                fetchProducts()
                toast.value = "Product ${product.title} updated successfully!"
            } catch (e: Exception){
                toast.value = "Error updating a product: ${e.message}"
            }
        }
    }

    fun deleteProduct(id: Int){
        viewModelScope.launch {
            try {
                repository.deleteProduct(id)
                fetchProducts()
                toast.value = "Deleted!"
            } catch (e: Exception){
                toast.value = "Error deleting a product: ${e.message}"
            }
        }
    }
}