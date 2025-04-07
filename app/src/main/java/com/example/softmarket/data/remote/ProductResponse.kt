package com.example.softmarket.data.remote

import com.example.softmarket.data.Product
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    val code: Int,
    val status: String,
    val message: String,
    val data: List<Product>
)
