package com.example.e_comget.screens.Card

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.example.e_comget.screens.Card.Components.BodyListSection
import com.example.e_comget.screens.Card.Components.HeadSection

@Composable
fun CardScreen(navControllerApp: NavHostController) {

    val context = LocalContext.current
    val background = Color.White
    val isDarkTheme = isSystemInDarkTheme()


    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView).isAppearanceLightStatusBars = !isDarkTheme
    }
    Scaffold(
        bottomBar = {
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(padding)
        ) {
            HeadSection(navControllerApp)
            BodyListSection(navControllerApp)

        }
    }
}