package com.example.getmarketadmin.screens.data

//Product Image Name must change to the URL to the image or a byte Array
data class ProductDetail(
    val productId : Int,
    val productName: String,
    val productPrice: String,
    val productDescription: String,
    val availableColorList: List<String>? = emptyList(),
    val availableSizeList: List<String>? = emptyList(),
    val productCategory: String?,
    val productRemainingStock: Int? = 0,
    val productImageURLList: List<String> = emptyList()
)

//productImageNameList -----------------------

val product = ProductDetail(
    productId = 0,
    productName = "Steph Curry",
    productPrice = "150_000",
    productCategory = "Vetements",
    productDescription = "This is the steph curry new brand",
    productRemainingStock = 15,
    productImageURLList = listOf("pro1","pro2","pro3","pro4", "pro5"),
    availableColorList = listOf("Black", "Navy Blue", "Red", "Gray", "White"),
    availableSizeList = listOf("S", "M", "L", "XL")
)
