package com.example.e_comget.Datoum.model

import com.example.e_comget.Datoum.model.item.ProductDetail

data class ProductDetailWrapper(
    val product: List<ProductDetail> = emptyList(),
    val size: Int
)