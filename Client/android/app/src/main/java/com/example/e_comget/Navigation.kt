 package com.example.e_comget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.screens.Card.CardScreen
import com.example.e_comget.screens.Home.Components.ProductDetailsScreen
import com.example.e_comget.screens.Home.Components.ProductOrderScreen
import com.example.e_comget.screens.Home.HomeScreen
import com.example.e_comget.screens.MyAccount.ProfileScreen
import com.example.e_comget.screens.MyAccount.SignIn.LoginScreen
import com.example.e_comget.screens.MyAccount.SignIn.SignUpScreen
import com.example.e_comget.screens.Routes.MainScreens
import com.example.e_comget.screens.Routes.MyAccountScreens
import com.example.e_comget.screens.Routes.ProductScreens
import com.example.e_comget.screens.data.BottomNavigationItem

    @Composable
    fun App(){

        AppNavigation()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomNavigationBar(navControllerApp: NavHostController){
        var navigationSelectedItem by remember {
            mutableStateOf(0)
        }

        var navController = rememberNavController()
        Scaffold (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth(),
            bottomBar= {
                NavigationBar(
                    containerColor = Color.White,
                    modifier = Modifier
                        .height(80.dp)
                ){
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
                startDestination = MainScreens.Home.route,
                modifier = Modifier.padding(paddingValues = paddingValues)
            ) {
                composable(MainScreens.Home.route) {
                    HomeScreen(navControllerApp = navControllerApp)
                }
                composable(MainScreens.Card.route) {
                    CardScreen(navController = navController)
                }
                composable(MainScreens.Profile.route) {
                    ProfileScreen(navControllerApp = navControllerApp, navController = navController)
                }
            }
        }
    }

    @Composable
    fun AppNavigation(){
        var navControllerApp = rememberNavController()


        Box{
            NavHost(navController = navControllerApp, startDestination = "bottomNavigation"){
                composable("bottomNavigation") {
                    BottomNavigationBar(navControllerApp = navControllerApp)
                }
                composable(ProductScreens.ProductDetails.route) {
                    ProductDetailsScreen(
                        navControllerApp = navControllerApp
                    )
                }
                composable(ProductScreens.ProductOrder.route) {
                    ProductOrderScreen(navControllerApp = navControllerApp)
                }
                composable(MyAccountScreens.Login.route) {
                    LoginScreen(navControllerApp = navControllerApp)
                }
                composable(MyAccountScreens.SignUp.route) {
                    SignUpScreen(navControllerApp = navControllerApp)
                }
            }
        }
    }

    @Composable
    @Preview
    fun BottomNavigationBarPreview(){
        var navControllerApp = rememberNavController()
        BottomNavigationBar(navControllerApp);
    }
