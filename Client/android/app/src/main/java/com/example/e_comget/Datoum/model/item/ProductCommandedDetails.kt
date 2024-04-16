package com.example.e_comget.Datoum.model.item

data class ProductCommandedDetails(
    val productId: Int,
    val productName: String,
    val productPrice: String,
    val productDescription: String,
    val productSizeChosen: String,
    val productColorChosen: String,
    val productCategory: String?,
    val productIsPaid: Boolean
)

val tempProductCommandedDetails = listOf(
    ProductCommandedDetails(
        1,
        "T-shirt GET2024",
        "20000",
        "Ceci est la description du produit",
        "M",
        "Red",
        "Vetement",
        false
    ),
    ProductCommandedDetails(
        2,
        "Polo GET2024",
        "20000",
        "Ceci est la description du produit",
        "XL",
        "Navy Blue",
        "Vetement",
        true
    ),
    ProductCommandedDetails(
        3,
        "Sweat",
        "20000",
        "Ceci est la description du produit",
        "M",
        "Red",
        "Vetement",
        false
    ),
    ProductCommandedDetails(
        4,
        "Jogging",
        "20000",
        "Ceci est la description du produit",
        "M",
        "Red",
        "Vetement",
        false
    ),
    ProductCommandedDetails(
        5,
        "T-shirt GET2024",
        "20000",
        "Ceci est la description du produit",
        "M",
        "Red",
        "Vetement",
        true
    ),
)