package com.example.e_comget.Datoum.model

data class ProductDetailWrapper(
    val product: List<ProductDetail> = emptyList(),
    val size: Int
)