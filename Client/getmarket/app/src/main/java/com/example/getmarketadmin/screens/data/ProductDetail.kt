package com.example.getmarketadmin.screens.data

//Product Image Name must change to the URL to the image or a byte Array
data class ProductDetail(
    val productId : Int,
    val productName: String,
    val productPrice: String,
    val productDescription: String,
    val availableColorList: List<ColorItem>? = emptyList(),
    val availableSizeList: List<String>? = emptyList(),
    val productCategory: String?,
    val productImageURLList: List<String> = emptyList()
)

//productImageNameList -----------------------

val product = ProductDetail(
    productId = 0,
    productName = "T-shirt",
    productPrice = "21_000",
    productCategory = "Vêtements",
    productDescription = "T-shirt avec logo Get conçu par le GET 2024",
    productImageURLList = listOf("pro2","pro1","pro3","pro4", "pro5"),
    availableColorList = predefinedColorList,
    availableSizeList = listOf("S", "M", "L", "XL")
)
