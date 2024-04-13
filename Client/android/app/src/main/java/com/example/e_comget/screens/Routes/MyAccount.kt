package com.example.e_comget.screens.Routes

sealed class MyAccountScreens(val route: String) {
    data object SignIn: MyAccountScreens("signin_route")
    data object  Login: MyAccountScreens("login_route")
    data object  SignUp: MyAccountScreens("signup_route")
}
