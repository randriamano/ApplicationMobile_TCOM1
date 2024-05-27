package com.example.e_comget.Datoum.model.item

data class CommandItemToSend(
    val productId: Int,
    val studentId: Int,
    val productSizeChosen: String,
    val productColorChosen: ColorItem,
    val productIsPayed: Boolean
)