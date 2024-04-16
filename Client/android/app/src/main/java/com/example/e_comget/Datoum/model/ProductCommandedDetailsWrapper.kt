package com.example.e_comget.Datoum.model

import com.example.e_comget.Datoum.model.item.ProductCommandedDetails

data class ProductCommandedDetailsWrapper(
    val products: List<ProductCommandedDetails> = emptyList(),
)