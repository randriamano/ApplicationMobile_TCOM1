package com.example.e_comget.Datoum

import com.example.e_comget.Datoum.model.AuthenticationWrapper
import com.example.e_comget.Datoum.model.ProductCommandedDetailsWrapper
import com.example.e_comget.Datoum.model.ProductDetailWrapper
import com.example.e_comget.Datoum.model.item.ProductDetail
import com.example.e_comget.Datoum.model.item.UserItemToSend
import com.example.e_comget.Datoum.model.item.UserLoginItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class Nom(
    val nom: String = ""
)

interface GetApi {
    @GET("/api/products")
    suspend fun getProducts(): Response<ProductDetailWrapper>

    @GET("/api/products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Response<ProductDetail>

    @GET("/api/products/category/{category}")
    suspend fun getProductsByCategoryName(@Path("category") category: String): Response<ProductDetailWrapper>

    @POST("/api/student/signup?key=admin")
    suspend fun register(@Body requestData: UserItemToSend): Response<AuthenticationWrapper>

    @POST("/api/student/login")
    suspend fun login(@Body requestData: UserLoginItem): Response<AuthenticationWrapper>

    @GET("/api/command/{category}")
    suspend fun getCommandedProducts(@Path("category") category: String): Response<ProductCommandedDetailsWrapper>

    @GET("/")
    suspend fun getNom(): Response<Nom>
}