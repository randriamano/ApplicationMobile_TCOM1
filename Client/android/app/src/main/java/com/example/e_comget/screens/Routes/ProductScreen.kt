package com.example.e_comget.screens.Routes

sealed class ProductScreens(val route: String){
    object ProductDetails: ProductScreens("product_details_route")
    object  ProductOrder: ProductScreens("product_order_route")
}