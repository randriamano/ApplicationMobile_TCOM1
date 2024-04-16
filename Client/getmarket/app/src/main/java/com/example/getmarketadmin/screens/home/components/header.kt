package com.example.getmarketadmin.screens.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getmarketadmin.R
import com.example.getmarketadmin.ui.theme.ButtonColor


//Main header
@Composable
fun Header(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
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
                    color = ButtonColor,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                textAlign = TextAlign.Center

            )
            Text(
                text = " Market",
                style = TextStyle(
                    color = ButtonColor,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Light
                ),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "search",
                tint = Color.Black
            )
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.tune_24px),
                contentDescription = "search",
                tint = Color.Black
            )
        }

    }

}