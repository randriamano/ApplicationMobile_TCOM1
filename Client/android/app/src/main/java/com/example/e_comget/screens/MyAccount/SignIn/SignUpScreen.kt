package com.example.e_comget.screens.MyAccount.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.R
import com.example.e_comget.screens.MyAccount.SignIn.components.ButtonComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.ClickableLoginTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.HeadingTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.MyTextFieldComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.NormalTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.PasswordTextFieldComponent
import com.example.e_comget.screens.Routes.MainScreens
import com.example.e_comget.screens.Routes.MyAccountScreens

@Composable
fun SignUpScreen(navControllerApp: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
            ) {
            Spacer(Modifier.height(10.dp))
            IconButton(onClick = {
                navControllerApp.navigate("bottomNavigation")
//                navController.navigate(MainScreens.Home.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.close_24px),
                    contentDescription = "close"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 28.dp, end = 28.dp, top = 10.dp),
        ) {
            NormalTextComponent("Hey there, ")
            HeadingTextComponent("Create an Account")
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(labelValue = " First name", painterResource = painterResource(id = R.drawable.ic_profile) )
            MyTextFieldComponent(labelValue = " Last name", painterResource = painterResource(id = R.drawable.ic_profile))
            MyTextFieldComponent(labelValue = " Email", painterResource = painterResource(id = R.drawable.mail_24px))
            PasswordTextFieldComponent(labelValue = " Password", painterResource = painterResource(id = R.drawable.lock_24px))
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(value = "Register")
            Spacer(modifier = Modifier.height(10.dp))
            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                navControllerApp.navigate(MyAccountScreens.Login.route)
            })
        }

    }
}

@Preview
@Composable
fun SignUpScreenPreview(){
    val navController = rememberNavController()
    SignUpScreen(navControllerApp = navController)
}


