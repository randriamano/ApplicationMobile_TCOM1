package com.example.e_comget.screens.Card.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun HeadSection(
    navControllerApp: NavHostController
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        TopAppBarWithBackArrow(navControllerApp)
    }
}

//bouton retour
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackArrow(navControllerApp: NavController) {
    TopAppBar(
        title = {
            Text(text = "Liste des commandes", modifier = Modifier.padding(start = 4.dp))
                },
        navigationIcon = {
            Icon(
                Icons.TwoTone.ShoppingCart,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    )
}

