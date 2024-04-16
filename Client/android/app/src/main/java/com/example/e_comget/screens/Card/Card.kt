package com.example.e_comget.screens.Card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.e_comget.screens.Card.Components.BodyListSection
import com.example.e_comget.screens.Card.Components.HeadSection

@Composable
fun CardScreen(navControllerApp: NavHostController) {
    Scaffold(
        bottomBar = {
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HeadSection(navControllerApp)
            BodyListSection(navControllerApp)

        }
    }
}