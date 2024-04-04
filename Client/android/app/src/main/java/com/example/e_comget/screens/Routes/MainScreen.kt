package com.example.e_comget.screens.Routes

sealed class MainScreens(val route: String){
    object Home: MainScreens("home_route")
    object  Card: MainScreens("card_route")
    object  Profile: MainScreens("profile_route")
}