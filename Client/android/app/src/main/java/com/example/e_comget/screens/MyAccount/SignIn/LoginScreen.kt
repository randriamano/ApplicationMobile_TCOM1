package com.example.e_comget.screens.MyAccount.SignIn

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.DataStoreViewModel
import com.example.e_comget.Datoum.model.registration.LoginViewModel
import com.example.e_comget.Datoum.model.registration.UIEvent
import com.example.e_comget.MainViewModel
import com.example.e_comget.R
import com.example.e_comget.Routes.MyAccountScreens
import com.example.e_comget.screens.MyAccount.SignIn.components.ButtonComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.ClickableLoginTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.HeadingTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.MyTextFieldComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.NormalTextComponent
import com.example.e_comget.screens.MyAccount.SignIn.components.PasswordTextFieldComponent

@Composable
fun LoginScreen(
    navControllerApp: NavHostController,
    mainViewModel: MainViewModel,
    loginViewModel: LoginViewModel = viewModel(),
) {
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()

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
            NormalTextComponent("Bienvenu")
            HeadingTextComponent("Se connecter")

            LazyColumn {
                item {
                    MyTextFieldComponent(
                        labelValue = "Numéro étudiant (001-L1)",
                        painterResource = painterResource(id = R.drawable.id_card_24px),
                        onTextSelected = {
                            loginViewModel.onEvent(UIEvent.IdNumChanged(it))
                        })

                    PasswordTextFieldComponent(
                        labelValue = "Mot de passe",
                        painterResource = painterResource(id = R.drawable.lock_24px),
                        onTextSelected = {
                            loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                        })

                    Spacer(modifier = Modifier.height(50.dp))

                    ButtonComponent(
                        value = "Connexion",
                        onButtonClicked = {
                            loginViewModel.login(mainViewModel)
//                            navControllerApp.popBackStack()
                        })
                    Spacer(modifier = Modifier.height(10.dp))

                    ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                        navControllerApp.navigate(MyAccountScreens.SignUp.route)
                    })

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (mainViewModel.uiStateAuthenticatedUser.value.isLoading) {
                            CircularProgressIndicator()
                        } else if (!mainViewModel.uiStateAuthenticatedUser.value.error.isNullOrEmpty()) {
                            Text(text = "Impossible de connecter")
                        } else {
                            if (mainViewModel.uiStateAuthenticatedUser.value.data?.student != null) {

                                if (mainViewModel.uiStateAuthenticatedUser.value.data?.student?.studentId != -1) {
                                    dataStoreViewModel.updateIsLoggedIn(true)
                                    dataStoreViewModel.updateUserCardNum(mainViewModel.uiStateAuthenticatedUser.value.data?.student!!.studentCardNum)
                                    dataStoreViewModel.updtadeUserName(mainViewModel.uiStateAuthenticatedUser.value.data?.student!!.studentName)
                                    dataStoreViewModel.updateUserFirstName(mainViewModel.uiStateAuthenticatedUser.value.data?.student!!.studentFirstname)
                                    dataStoreViewModel.updateUserId(mainViewModel.uiStateAuthenticatedUser.value.data?.student!!.studentId)

                                    Log.d("", "=====================================================")
                                    Log.d("", "${mainViewModel.uiStateAuthenticatedUser.value.data?.student!!.studentId}")

                                    navControllerApp.navigate("bottomNavigation")
                                } else {
                                    mainViewModel.reinitializeAuthenticatedUser()
                                    Text(text = "Impossible de creer votre compte")
                                }
                            }
                        }
                    }

                }
            }

        }

    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
//    LoginScreen(navControllerApp = navController)
}
