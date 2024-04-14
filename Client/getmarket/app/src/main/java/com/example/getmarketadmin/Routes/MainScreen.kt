package com.example.e_comget.screens.Routes

sealed class MainScreens(val route: String){
    object Home: MainScreens("home_route")
    object  Order: MainScreens("order_route")
    object  Profile: MainScreens("profile_route")
    object  AddNewProduct: MainScreens("add_new_product_route")
    object ProductDetails: MainScreens("product_details_route")
}