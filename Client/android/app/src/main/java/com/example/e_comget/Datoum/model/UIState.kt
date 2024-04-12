package com.example.e_comget.Datoum.model

import com.example.e_comget.Datoum.Nom

data class UIState(
    //var r
    val isLoading: Boolean = false,
    val error: String? = null,
    val data : List<ProductDetail> = emptyList()
)

data class UIStateProduct(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: ProductDetail? = null
)

data class UIStateName(
    val error: String? = null,
    val isLoading: Boolean = false,
    val data: Nom = Nom("coucou")
)