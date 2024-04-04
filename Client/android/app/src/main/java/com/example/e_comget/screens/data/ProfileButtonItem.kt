package com.example.e_comget.screens.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.sharp.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.e_comget.screens.Routes.MyAccountScreens

data class BtnItem (
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route : String = ""
)

val btnList = listOf(
    BtnItem(
        label = "Sign In",
        icon = Icons.Sharp.Home,
        route = MyAccountScreens.SignIn.route
    )
)

data class ProfileItem (
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route : String = ""
)

val ProfileItemList = listOf(
    ProfileItem(
        label = "Notifications",
        icon = Icons.Outlined.Notifications,
        route = "vers home/notification"
    ),
    ProfileItem(
        label = "Commandes",
        icon = Icons.Outlined.Sell,
        route = "vers Cart"
    ),
    ProfileItem(
        label = "Edit profil info",
        icon = Icons.Outlined.EditNote,
        route = "vers editProfileScreen"
    ),
    ProfileItem(
        label = "Log out",
        icon = Icons.Outlined.Logout,
        route = "vers editProfileScreen"
    )
)