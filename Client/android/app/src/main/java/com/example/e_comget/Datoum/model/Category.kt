package com.example.e_comget.Datoum.model

data class Category(
    var id: Int,
    var categoryName: String,
)

val categoryList = listOf(
    Category(1, "Tous les categories"),
    Category(2, "Vetements"),
    Category(3, "Billets"),
    Category(4, "Goodies")
)