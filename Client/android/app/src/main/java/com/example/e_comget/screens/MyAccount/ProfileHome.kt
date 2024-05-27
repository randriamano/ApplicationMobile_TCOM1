package com.example.e_comget.screens.MyAccount

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.DataStoreViewModel
import com.example.e_comget.Datoum.model.item.ProfileItemList
import com.example.e_comget.MainViewModel
import com.example.e_comget.R
import com.example.e_comget.screens.MyAccount.SignIn.components.HeadingTextComponent
import com.example.e_comget.ui.theme.GrayColor
import com.example.e_comget.ui.theme.TextColor


@Composable
fun ProfileHomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    mainViewModel: MainViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Icon(
                painter = painterResource(id = R.drawable.account_circle_24px),
                contentDescription = "close",
                modifier = Modifier.size(128.dp)
            )
            HeadingTextComponent("${dataStoreViewModel.userName.collectAsState(initial = true).value}")

            ItemDisp(
                navController = navController,
                dataStoreViewModel = dataStoreViewModel,
                mainViewModel = mainViewModel
            )

        }

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemDisp(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel,
    mainViewModel: MainViewModel
) {

    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    Surface(
        modifier = Modifier.background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            contentPadding = PaddingValues(vertical = 56.dp, horizontal = 12.dp)
        ) {
            items(ProfileItemList.size) { index ->
                val btn = ProfileItemList[index]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (btn.label.equals("Se déconnecter")) {
                                dataStoreViewModel.updateIsLoggedIn(false);
                                dataStoreViewModel.updateUserCardNum("");
                                dataStoreViewModel.updateUserFirstName("");
                                dataStoreViewModel.updtadeUserName("")

                                mainViewModel.reinitializeAuthenticatedUser()
                            }
//                            navigationSelectedItem = index
//                            navController.navigate(btn.route) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
                        }
                        .padding(vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            btn.icon,
                            contentDescription = btn.label
                        )

                        Spacer(modifier = Modifier.padding(5.dp))

                        Text(
                            text = btn.label,
                            modifier = Modifier.weight(1f),
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal
                            ),
                            color = TextColor,
                        )

                        Icon(Icons.Outlined.NavigateNext, contentDescription = "navigation next")
                    }
                }
                DividerComponent()
            }
        }

    }
}

@Composable
fun DividerComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )
    }
}

@Preview
@Composable
fun ProfileHomeScreenPreview() {
    val navController = rememberNavController()
//    ProfileHomeScreen(navController = navController)
}