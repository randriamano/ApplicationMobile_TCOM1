package com.example.e_comget.Datoum

import com.example.e_comget.Datoum.model.ProductDetail
import com.example.e_comget.Datoum.model.ProductDetailWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class Nom(
    val nom: String = ""
)

interface GetApi{
    @GET("/api/products")
    suspend fun getProducts(): Response<ProductDetailWrapper>

    @GET("/api/products/{id}")
    suspend fun getProductById(@Path("id") id : Int): Response<ProductDetail>

    @GET("/")
    suspend fun getNom(): Response<Nom>
}