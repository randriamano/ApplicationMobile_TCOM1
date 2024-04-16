package com.example.e_comget.Datoum.model

import com.example.e_comget.Datoum.Nom
import com.example.e_comget.Datoum.model.item.ProductCommandedDetails
import com.example.e_comget.Datoum.model.item.ProductDetail

data class UIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<ProductDetail> = emptyList()
)

data class UIStateProduct(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: ProductDetail? = null
)

data class UIStateProductCommanded(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: List<ProductCommandedDetails>? = emptyList()
)

data class UIStateName(
    val error: String? = null,
    val isLoading: Boolean = false,
    val data: Nom = Nom("coucou")
)

data class UIStateAuthenticatedUser(
    val error: String? = null,
    val isLoading: Boolean = false,
    val data: AuthenticationWrapper? = null
)