package com.example.e_comget.screens.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Book
import androidx.compose.material.icons.sharp.Bookmark
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.List
import androidx.compose.material.icons.sharp.Person
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
                label = "Produits",
                icon = Icons.Sharp.Home,
                route = MainScreens.Home.route
            ),
            BottomNavigationItem(
                label = "Commandes",
                icon = Icons.Sharp.Bookmark,
                route = MainScreens.Order.route
            ),
            BottomNavigationItem(
                label = "Compte",
                icon = Icons.Sharp.Person,
                route = MainScreens.Profile.route
            )
        )
    }
}