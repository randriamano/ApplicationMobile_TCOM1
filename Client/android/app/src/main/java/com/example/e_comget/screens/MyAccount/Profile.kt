package com.example.e_comget.screens.MyAccount

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.DataStoreViewModel
import com.example.e_comget.MainViewModel
import com.example.e_comget.screens.MyAccount.SignIn.SignUpScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navControllerApp: NavHostController,
    mainViewModel: MainViewModel
) {
    val dataViewModel: DataStoreViewModel = hiltViewModel()

    if (dataViewModel.isLoggedIn.collectAsState(initial = true).value.equals(false)) {
        SignUpScreen(navControllerApp = navControllerApp, mainViewModel = mainViewModel)
    } else {
        ProfileHomeScreen(navController = navControllerApp, mainViewModel = mainViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
//    ProfileScreen(navControllerApp = navController, navController)
}


