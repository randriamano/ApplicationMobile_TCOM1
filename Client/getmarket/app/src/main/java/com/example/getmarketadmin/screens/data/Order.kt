package com.example.getmarketadmin.screens.data


data class Order(
    val orderName: String,
    val orderClass: String,
    val orderProduct: String,
    val orderProductCategory: String,
    val orderNumber: Int,
    val orderColor: String,
    val orderSize: String,
    val isDeliver: Boolean
)

val orderList: List<Order> = listOf(
    Order(
        orderName = "RANDRIMANOHISOA",
        orderClass = "015-M1",
        orderProduct = "Sac",
        orderProductCategory = "Goodies",
        orderNumber = 2,
        orderColor = "",
        orderSize = "",
        isDeliver = false
    ),

    Order(
        orderName = "RANDRIMANOHISOA",
        orderClass = "015-M1",
        orderProduct = "Sac",
        orderProductCategory = "Goodies",
        orderNumber = 2,
        orderColor = "",
        orderSize = "",
        isDeliver = false
    ),
    Order(
        orderName = "RAMAROKOTO",
        orderClass = "010-M1",
        orderProduct = "T-shirt",
        orderProductCategory = "Vêtement",
        orderNumber = 1,
        orderColor = "Blanc",
        orderSize = "M",
        isDeliver = false
    ),
    Order(
        orderName = "RANDRIANTSIROFO",
        orderClass = "011-M1",
        orderProduct = "Porte clés",
        orderProductCategory = "Goodies",
        orderNumber = 5,
        orderColor = "",
        orderSize = "",
        isDeliver = false
    ),
    Order(
        orderName = "RAMANAKOTO",
        orderClass = "012-M1",
        orderProduct = "T-shirt hik",
        orderProductCategory = "Vêtement",
        orderNumber = 1,
        orderColor = "Blue",
        orderSize = "XL",
        isDeliver = false
    ),
    Order(
        orderName = "RAKOTONANDRASANA",
        orderClass = "013-M1",
        orderProduct = "Mug",
        orderProductCategory = "Goodies",
        orderNumber = 2,
        orderColor = "",
        orderSize = "",
        isDeliver = false
    )
)