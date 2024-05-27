package com.example.e_comget.Datoum.model.item

data class CommandStateResponse(
    val commandId: Int,
    val productId: Int,
    val studentId: Int,
    val productSizeChosen: String,
    val productColorChosen: String,
    val commandDate: String
)