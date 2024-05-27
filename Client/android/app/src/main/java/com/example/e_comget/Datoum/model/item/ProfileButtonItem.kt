package com.example.e_comget.Datoum.model.item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.e_comget.Routes.MainScreens

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
        route = MainScreens.Card.route
    ),
    ProfileItem(
        label = "Se déconnecter",
        icon = Icons.Outlined.Logout,
        route = "vers signInScreen"
    )
)