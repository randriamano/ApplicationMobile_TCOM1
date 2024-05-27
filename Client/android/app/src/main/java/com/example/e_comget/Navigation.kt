package com.example.e_comget

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.Datoum.model.item.BottomNavigationItem
import com.example.e_comget.Datoum.model.item.CommandItem
import com.example.e_comget.Datoum.model.item.ProductDetail
import com.example.e_comget.Routes.MainScreens
import com.example.e_comget.Routes.MyAccountScreens
import com.example.e_comget.Routes.ProductScreens
import com.example.e_comget.screens.Card.CardScreen
import com.example.e_comget.screens.Card.Components.CommandCategoryDetailsScreen
import com.example.e_comget.screens.Home.Components.ProductDetailsScreen
import com.example.e_comget.screens.Home.Components.ProductOrderScreen
import com.example.e_comget.screens.Home.Components.SearchBar
import com.example.e_comget.screens.Home.HomeScreen
import com.example.e_comget.screens.MyAccount.ProfileScreen
import com.example.e_comget.screens.MyAccount.SignIn.LoginScreen
import com.example.e_comget.screens.MyAccount.SignIn.SignUpScreen
import com.example.e_comget.screens.Start.StartScreen
import com.example.e_comget.ui.theme.Bg40
import com.example.e_comget.ui.theme.BgButtonColor

//TODO
//Fetching product commanded before navigating into ProductCommandedDetails

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {
    val mainViewModel: MainViewModel = hiltViewModel();
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()
    AppNavigation(mainViewModel = mainViewModel, dataStoreViewModel = dataStoreViewModel)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomNavigationBar(navControllerApp: NavHostController, mainViewModel: MainViewModel) {
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }

    var productList: List<ProductDetail> by remember {
        mutableStateOf(emptyList())
    }



    var navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        bottomBar = {
            NavigationBar(
                containerColor = Bg40,
                modifier = Modifier
                    .height(80.dp),
            ) {
                BottomNavigationItem().bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->
                        NavigationBarItem(
                            selected = index == navigationSelectedItem,
                            colors = NavigationBarItemDefaults.colors(indicatorColor = BgButtonColor, ),
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
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = MainScreens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(MainScreens.Home.route) {
                productList = mainViewModel.uiState.value.data

                HomeScreen(
                    navControllerApp = navControllerApp,
//                    productList = productList,
                    uiState = mainViewModel.uiState.value,
                    onGetProduct = { mainViewModel.getProduct() },
                    mainViewModel = mainViewModel
                )
            }
            composable(MainScreens.Card.route) {
                CardScreen(navControllerApp = navControllerApp)
            }
            composable(MainScreens.Profile.route) {
                ProfileScreen(navControllerApp = navControllerApp, mainViewModel = mainViewModel)
//                    ProfileHomeScreen(navController = navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(mainViewModel: MainViewModel, dataStoreViewModel: DataStoreViewModel) {
    var navControllerApp = rememberNavController()
    var productSearchedList: List<ProductDetail> by remember {
        mutableStateOf(emptyList())
    }

    Box {
        NavHost(navController = navControllerApp, startDestination = "startScreen") {
            composable("startScreen") {
                StartScreen(navControllerApp = navControllerApp)
            }
            composable("bottomNavigation") {
                BottomNavigationBar(navControllerApp = navControllerApp, mainViewModel)
            }
            composable(ProductScreens.ProductDetails.route + "/{productId}") { backStateEntry ->

                val productIdString = backStateEntry.arguments?.getString("productId")
                val productId = productIdString?.toIntOrNull() ?: 4

                if (productId != null) {
                    ProductDetailsScreen(
                        navControllerApp = navControllerApp,
                        onGetProductDetails = {
                            mainViewModel.getProductById(productId)
                        },
                        mainViewModel = mainViewModel
                    )
                }
            }
            composable(ProductScreens.ProductOrder.route) {
                ProductOrderScreen(navControllerApp = navControllerApp)
            }
            composable(MyAccountScreens.Login.route) {
                LoginScreen(navControllerApp = navControllerApp, mainViewModel = mainViewModel)
            }
            composable(MyAccountScreens.SignUp.route) {
                SignUpScreen(
                    navControllerApp = navControllerApp,
                    mainViewModel = mainViewModel,
                    dataStoreViewModel = dataStoreViewModel
                )
            }
            composable("commandDetails/{productCommandRoute}") { backStateEntry ->
                val productCommandRoute = backStateEntry.arguments?.getString("productCommandRoute")
                var commandItem: CommandItem = CommandItem();

                for (value in CommandItem().getCommandItems()) {
                    if (value.productCategoryNavigationRoute.equals(productCommandRoute)) {
                        commandItem = value;
                        break;
                    }
                }

                CommandCategoryDetailsScreen(
                    navController = navControllerApp,
                    commandItem = commandItem,
                    mainViewModel = mainViewModel,
                    onGetCommandCategoryProducts = {
                        mainViewModel.getCommandedProducts(commandItem.productCategoryEndpointName)
                    }
                )
            }
            composable(ProductScreens.Search.route) {
                SearchBar(
                    navControllerApp = navControllerApp,
                    uiState = mainViewModel.uiStateProductSearched.value,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun BottomNavigationBarPreview() {
    var navControllerApp = rememberNavController()
    BottomNavigationBar(navControllerApp, mainViewModel = hiltViewModel());
}
