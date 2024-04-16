package com.example.e_comget.Routes

sealed class MyChartScreen(val route: String) {
    data object Vetement : MyAccountScreens("vetement_route")
    data object Billet : MyAccountScreens("billet_route")
    data object Goodies : MyAccountScreens("goodies_route")
}
