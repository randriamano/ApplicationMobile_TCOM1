package com.example.getmarketadmin.screens.home

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.EmojiFoodBeverage
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.getmarketadmin.R
import com.example.getmarketadmin.ui.theme.Bg40

@Composable
fun DropDownMenu() {

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                Icons.Outlined.Tune,
                contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =  Modifier.background(Bg40)
        ) {
            DropdownMenuItem(
                text = { Text("Tous les produits") },
                onClick = { /* Handle edit! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Apps,
                        contentDescription = null
                    )
                })
            DropdownMenuItem(
                text = { Text("Vêtements") },
                onClick = { /* Handle settings! */ },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.apparel_24px),
                        contentDescription = null
                    )
                })
           // HorizontalDivider()
            DropdownMenuItem(
                text = { Text("Billets") },
                onClick = { /* Handle send feedback! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.ConfirmationNumber,
                        contentDescription = null
                    )
                },
                )

            DropdownMenuItem(
                text = { Text("Goodies") },
                onClick = { /* Handle send feedback! */ },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.EmojiFoodBeverage,
                        contentDescription = null
                    )
                },
            )
        }
    }
}