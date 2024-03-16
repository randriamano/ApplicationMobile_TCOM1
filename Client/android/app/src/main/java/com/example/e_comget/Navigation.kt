package com.example.e_comget

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Person
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.e_comget.screens.Card.CardScreen
import com.example.e_comget.screens.Details.DetailsScreenWrapper
import com.example.e_comget.screens.Home.HomeScreen
import com.example.e_comget.screens.MyAccount.ProfileScreen

sealed class Screens(val route: String){
        object Home: Screens("home_route")
        object  Card: Screens("card_route")
        object  Profile: Screens("profile_route")
    }

    data class BottomNavigationItem (
        val label: String = "",
        val icon: ImageVector = Icons.Filled.Home,
        val route : String = ""
    ){
        fun bottomNavigationItems() : List<BottomNavigationItem> {
            return listOf(
                BottomNavigationItem(
                    label = "Home",
                    icon = Icons.Sharp.Home,
                    route = Screens.Home.route
                ),
                BottomNavigationItem(
                    label = "Card",
                    icon = Icons.Sharp.ShoppingCart,
                    route = Screens.Card.route
                ),
                BottomNavigationItem(
                    label = "Profile",
                    icon = Icons.Sharp.Person,
                    route = Screens.Profile.route
                )
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomNavigationBar(){
        var navigationSelectedItem by remember {
            mutableStateOf(0)
        }

        var navController = rememberNavController()
        Scaffold (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth(),
            bottomBar = {
                NavigationBar {
                    BottomNavigationItem().bottomNavigationItems().forEachIndexed  { index, navigationItem ->
                        NavigationBarItem(
                            selected = index == navigationSelectedItem,
                            label = {
                                Text(navigationItem.label)
                            },
                            icon = {
                                Icon(
                                    navigationItem.icon,
                                    contentDescription = navigationItem.label
                                )
                            },
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ){
           paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route,
                modifier = Modifier.padding(paddingValues = paddingValues)) {
                composable(Screens.Home.route) {
                    HomeScreen(navController = navController)
                }
                composable(Screens.Card.route) {
                    CardScreen(navController = navController)
                }
                composable(Screens.Profile.route) {
                    ProfileScreen(navController = navController)
                }
            }
        }
    }

    @Composable
    @Preview
    fun BottomNavigationBarPreview(){
        BottomNavigationBar();
    }
