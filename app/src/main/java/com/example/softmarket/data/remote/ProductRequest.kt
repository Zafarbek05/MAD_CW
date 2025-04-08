package com.example.softmarket.data.remote

data class ProductRequest(
    val title: String,
    val color: String,
    val age: Int,
    val integer_one: Int,
    val integer_two: Int,
    val price: Double,
    val url: String,
    val size: Int,
    val description: String,
    val integer_three: Int
)