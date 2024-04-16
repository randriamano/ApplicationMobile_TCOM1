package com.example.getmarketadmin.screens.profile

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.example.e_comget.screens.Routes.MainScreens
import com.example.getmarketadmin.ui.theme.Bg40


@Composable
fun ProfileHomeScreen(navControllerApp: NavHostController) {

    val context = LocalContext.current
    val background = Bg40
    val isDarkTheme = isSystemInDarkTheme()


    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView).isAppearanceLightStatusBars = !isDarkTheme
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = "close",
                modifier = Modifier.size(128.dp)
            )
            HeadingTextComponent("Admin") //anaran le Compte

            Spacer(modifier = Modifier.height(50.dp))

            ItemDisp(navController = navControllerApp)

        }

    }
}

@Composable
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemDisp (navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { /*TODO*/
                    navController.navigate(MainScreens.Login.route)
                }
                .padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Logout,
                    contentDescription = "Se déconnecter"
                )
                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = "Se déconnecter",
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ),
                    color = Color.Black,
                )

                Icon(Icons.Outlined.NavigateNext, contentDescription = "navigation next")

            }

        }

        DividerComponent()

    }

}


@Composable
fun DividerComponent(){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

