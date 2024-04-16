package com.example.getmarketadmin.screens.data

data class ColorItem(
    val colorItemCode: String,
    val colorItemName: String
)

val predefinedColorList: List<ColorItem> = listOf(
    ColorItem(
        colorItemCode = "#B99470",
        colorItemName = "Beige"
    ),
    ColorItem(
        colorItemCode = "#9C0202",
        colorItemName = "Rouge"
    ),

    ColorItem(
        colorItemCode = "#DC6B19",
        colorItemName = "Orange"
    ),

    ColorItem(
        colorItemCode = "#1A2692",
        colorItemName = "Navy blue"
    ),

    )