package com.example.e_comget.screens.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.e_comget.screens.Routes.MainScreens

data class BottomNavigationItem (
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route : String = ""
){
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Acceuil",
                icon = Icons.Sharp.Home,
                route = MainScreens.Home.route
            ),
            BottomNavigationItem(
                label = "Commander",
                icon = Icons.Sharp.ShoppingCart,
                route = MainScreens.Card.route
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Sharp.Person,
                route = MainScreens.Profile.route
            )
        )
    }
}