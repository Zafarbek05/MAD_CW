package com.example.softmarket.data

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String,

    @SerializedName("color")
    val provider: String,

    @SerializedName("age")
    val category: Int,

    @SerializedName("integer_one")
    val targetPlatforms: Int,

    @SerializedName("integer_two")
    val billing: Int,

    @SerializedName("price")
    val price: Double,

    @SerializedName("url")
    val logoUrl: String,

    @SerializedName("size")
    val size: Int,

    @SerializedName("description")
    val about: String,

    @SerializedName("integer_three")
    var quantity: Int = 1
)

fun getCategory(category: Int): String{
    return when (category) {
        0 -> "Engineering"
        1 -> "Productivity"
        2 -> "Graphics"
        else -> "Other"
    }
}

fun getPlatform(targetPlatforms: Int): String{
    return when (targetPlatforms) {
        0 -> "Windows"
        1 -> "macOS"
        2 -> "Linux"
        else -> "Other"
    }
}

fun getBilling(billing: Int): String{
    return when (billing) {
        0 -> "Monthly"
        1 -> "Annually"
        else -> "Other"
    }
}

fun setCategory(category: String): Int{
    return when (category) {
        "Engineering" -> 0
        "Productivity" -> 1
        "Graphics" -> 2
        else -> -1
    }
}

fun setPlatform(targetPlatforms: String): Int{
    return when (targetPlatforms) {
        "Windows" -> 0
        "macOS" -> 1
        "Linux" -> 2
        else -> -1
    }
}

fun setBilling(billing: String): Int{
    return when (billing) {
        "Monthly" -> 0
        "Annually" -> 1
        else -> -1
    }
}







