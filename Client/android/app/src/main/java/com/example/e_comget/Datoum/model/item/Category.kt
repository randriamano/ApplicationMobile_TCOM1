package com.example.e_comget.Datoum.model.item

data class Category(
    var id: Int,
    var categoryName: String,
    var categoryEndPointName: String = ""
)

val categoryList = listOf(
    Category(1, "Tous les categories", ""),
    Category(2, "Vêtements", "vetements"),
    Category(3, "Billets", "billets"),
    Category(4, "Goodies", "goodies")
)