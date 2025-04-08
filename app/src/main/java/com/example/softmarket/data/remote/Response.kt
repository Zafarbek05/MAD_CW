package com.example.softmarket.data.remote

import com.example.softmarket.data.Product

data class Response(
    val code: Int,
    val status: String,
    val message: String,
    val data: List<Product>
)
