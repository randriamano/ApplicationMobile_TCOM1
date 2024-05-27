package com.example.e_comget.screens.Home.Components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.e_comget.Routes.ProductScreens
import com.example.e_comget.ui.theme.Secondary


//Main header
@Composable
fun Header(navControllerApp: NavHostController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "GET",
                style = TextStyle(
                    color = Secondary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                textAlign = TextAlign.Center

            )
            Text(
                text = " Market",
                style = TextStyle(
                    color = Secondary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light
                ),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        IconButton(onClick = {
            navControllerApp.navigate(ProductScreens.Search.route)
            /*TODO*/
        }) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "search",
                tint = Color.Black
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Outlined.Notifications,
                contentDescription = "notification",
                tint = Color.Black
            )
        }



    }

}