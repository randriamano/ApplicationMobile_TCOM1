 package com.example.getmarketadmin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.getmarketadmin.screens.home.ProductDetailsScreen
import com.example.e_comget.screens.Routes.MainScreens
import com.example.e_comget.screens.data.BottomNavigationItem
import com.example.getmarketadmin.screens.command.OrderScreen
import com.example.getmarketadmin.screens.home.AddNewProductScreen
import com.example.getmarketadmin.screens.home.HomeScreen
import com.example.getmarketadmin.screens.profile.ProfileScreen
import com.example.getmarketadmin.ui.theme.Bg
import com.example.getmarketadmin.ui.theme.BgButtonColor

 @Composable
fun App(){
    AppNavigation()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navControllerApp: NavHostController){

    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    var navController = rememberNavController()
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        bottomBar= {
            NavigationBar(
                containerColor = Bg,
                modifier = Modifier
                    .height(80.dp)
            ){
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
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
                        },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = BgButtonColor, )
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
            composable(MainScreens.Order.route) {
                OrderScreen(navControllerApp = navControllerApp)
            }
            composable(MainScreens.Profile.route) {
                ProfileScreen(navControllerApp = navControllerApp)
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
         composable(MainScreens.AddNewProduct.route) {
             AddNewProductScreen(navControllerApp = navControllerApp)
         }
         composable(MainScreens.ProductDetails.route){
             ProductDetailsScreen(navControllerApp = navControllerApp)
         }

     }
 }
}


@Composable
@Preview
fun BottomNavigationBarPreview(){
    var navControllerApp = rememberNavController()
    BottomNavigationBar(navControllerApp)
}
