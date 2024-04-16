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

@Composable
fun CategoryTitle(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "  Les produits",
                //TODO
                // change the text related to the category Selected
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                textAlign = TextAlign.Center

            )

        }


    }

}