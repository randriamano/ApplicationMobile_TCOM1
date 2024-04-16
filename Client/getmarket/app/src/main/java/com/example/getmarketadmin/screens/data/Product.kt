package com.example.getmarketadmin.screens.data

data class Product(
    val productId: Int,
    val productName: String,
    val productCategory: String,
    val productPrice: Long,
    val productImage: String
)

val productList: List<Product> = listOf(
    Product(
        productId = 0,
        productName = "Sac",
        productCategory = "Goodies",
        productPrice = 120000,
        productImage = "pro1"
    ),
    Product(
        productId = 1,
        productName = "T-shirt",
        productCategory = "Vêtements",
        productPrice = 150000,
        productImage = "pro2"
    ),
    Product(
        productId = 2,
        productName = "Porte clés",
        productCategory = "Goodies",
        productPrice = 150000,
        productImage = "pro3"
    ),
    Product(
        productId = 3,
        productName = "T-shirt hik",
        productCategory = "Vêtements",
        productPrice = 150000,
        productImage = "pro4"
    ),
    Product(
        productId = 4,
        productName = "Mug",
        productCategory = "Goodies",
        productPrice = 150000,
        productImage = "pro5"
    ),
    Product(
        productId = 0,
        productName = "Sac",
        productCategory = "Goodies",
        productPrice = 120000,
        productImage = "pro1"
    ),
    Product(
        productId = 1,
        productName = "T-shirt",
        productCategory = "Vêtements",
        productPrice = 150000,
        productImage = "pro2"
    ),
    Product(
        productId = 2,
        productName = "Porte clés",
        productCategory = "Goodies",
        productPrice = 150000,
        productImage = "pro3"
    ),
)