package com.example.e_comget.screens.data

//Product Image Name must change to the URL to the image or a byte Array
data class ProductDetail(
    val productId : Int,
    val productName: String,
    val productPrice: Long,
    val productDescription: String,
    val availableColorList: List<String>,
    val availableSizeList: List<String>,
    val productCategory: String,
    val productRemainingStock: Int,
    val productImageNameList: List<String>
)

val product = ProductDetail(
    productId = 0,
    productName = "Steph Curry",
    productPrice = 150_000,
    productCategory = "Vetements",
    productDescription = "This is the steph curry new brand",
    productRemainingStock = 15,
    productImageNameList = listOf("curry1","curry2","curry2","curry3","curry4"),
    availableColorList = listOf("Black", "Navy Blue", "Red", "Gray", "White"),
    availableSizeList = listOf("S", "M", "L", "XL")
)
