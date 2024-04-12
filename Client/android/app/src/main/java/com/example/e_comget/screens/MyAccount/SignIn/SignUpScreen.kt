package com.example.e_comget.screens.MyAccount.SignIn

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.Datoum.model.RegistationViewModel
import com.example.e_comget.Datoum.model.RegistrationUIState
import com.example.e_comget.Datoum.model.UIEvent
import com.example.e_comget.R
import com.example.e_comget.screens.MyAccount.SignIn.components.ButtonComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.ClickableLoginTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.HeadingTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.MyTextFieldComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.NormalTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.PasswordTextFieldComponent
import com.example.e_comget.screens.Routes.MyAccountScreens

@SuppressLint("UnrememberedMutableState")
@Composable
fun SignUpScreen(navControllerApp: NavHostController, registationViewModel: RegistationViewModel = viewModel() ){

    var RegistrationUIState : MutableState<RegistrationUIState> = mutableStateOf(RegistrationUIState())

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            NormalTextComponent("Bonjour, ")
            HeadingTextComponent("Créer un compte")
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = "Nom",
                painterResource = painterResource(id = R.drawable.ic_profile),
                onTextSelected = {
                    registationViewModel.onEvent(UIEvent.FirstNameChanged(it))
                } )

            MyTextFieldComponent(
                labelValue = "Prénom(s)",
                painterResource = painterResource(id = R.drawable.ic_profile),
                onTextSelected = {
                    registationViewModel.onEvent(UIEvent.LastNameChanged(it))
                })

            MyTextFieldComponent(
                labelValue = "Numéro étudiant (001-L1)",
                painterResource = painterResource(id = R.drawable.id_card_24px),
                onTextSelected = {
                    registationViewModel.onEvent(UIEvent.IdNumChanged(it))
                })

            PasswordTextFieldComponent(
                labelValue = "Mot de passe",
                painterResource = painterResource(id = R.drawable.lock_24px),
                onTextSelected = {
                    registationViewModel.onEvent(UIEvent.PasswordChanged(it))
                })
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(
                value = "Créer",
                onButtonClicked = {
                registationViewModel.onEvent(UIEvent.RegisterButtonClicked)
            })
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


