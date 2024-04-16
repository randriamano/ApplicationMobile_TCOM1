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
    productName = "Steph Curry",
    productPrice = "150_000",
    productCategory = "Vêtements",
    productDescription = "This is the steph curry new brand",
    productImageURLList = listOf("pro1","pro2","pro3","pro4", "pro5"),
    availableColorList = predefinedColorList,
    availableSizeList = listOf("S", "M", "L", "XL")
)
