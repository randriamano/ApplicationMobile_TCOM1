package com.example.e_comget.Datoum.model

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
        productName = "Chaussure",
        productCategory = "Vetements",
        productPrice = 120000,
        productImage = "curry1"
    ),
    Product(
        productId = 1,
        productName = "Chaussure",
        productCategory = "Vetements",
        productPrice = 150000,
        productImage = "curry2"
    ),
    Product(
        productId = 2,
        productName = "Chaussure",
        productCategory = "Vetements",
        productPrice = 150000,
        productImage = "curry3"
    ),
    Product(
        productId = 3,
        productName = "Chaussure",
        productCategory = "Vetements",
        productPrice = 150000,
        productImage = "curry4"
    ),
    Product(
        productId = 4,
        productName = "Chaussure",
        productCategory = "Vetements",
        productPrice = 150000,
        productImage = "curry1"
    )
)