package com.example.e_comget.Datoum.model.item

data class UserItem(
    val studentId: Int? = 0,
    val studentCardNum: String,
    val studentName: String,
    val studentFirstname: String,
    val studentPassword: String
)

data class UserItemToSend(
    val studentName: String,
    val studentFirstname: String,
    val studentCardNum: String,
    val studentPassword: String
)