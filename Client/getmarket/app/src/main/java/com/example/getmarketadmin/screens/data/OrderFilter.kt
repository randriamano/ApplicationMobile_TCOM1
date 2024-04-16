package com.example.getmarketadmin.screens.data

data class Filter(
    val filterLabel: String,
)

val filterList: List<Filter> = listOf(
    Filter(filterLabel = "Tous"),
    Filter(filterLabel = "En cours"),
    Filter(filterLabel = "Livré")

)