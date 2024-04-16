package com.example.e_comget.Routes

sealed class MainScreens(val route: String) {
    object Home : MainScreens("home_route")
    object Card : MainScreens("card_route")
    object Profile : MainScreens("profile_route")
}