package com.example.e_comget.Routes

sealed class ProductScreens(val route: String) {
    object ProductDetails : ProductScreens("product_details_route")
    object ProductOrder : ProductScreens("product_order_route")

    object Search : ProductScreens("search_route")
}