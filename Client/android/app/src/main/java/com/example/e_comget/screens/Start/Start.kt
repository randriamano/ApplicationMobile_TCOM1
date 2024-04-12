package com.example.e_comget.screens.Start

import android.app.Activity
import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.delay
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.e_comget.R
import com.example.e_comget.ui.theme.WhiteColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun StartScreen(navControllerApp: NavHostController) {

    val context = LocalContext.current
    val background = WhiteColor
    val isDarkTheme = isSystemInDarkTheme()


   SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView)?.isAppearanceLightStatusBars = !isDarkTheme
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
        ,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)

        ) {
            var isTimeFinished by remember { mutableStateOf(false) }

            SplashScreen(navController = navControllerApp)

            if (isTimeFinished){

                SecondSplashScreen()

            }
            LaunchedEffect(Unit) {
                delay(800L)
                isTimeFinished = true
            }
        }
    }

}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }


    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)

        navController.navigate("bottomNavigation")
    }


    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}


@Composable
fun SecondSplashScreen() {
    val offsetY = remember {
        Animatable(300f)
    }
    val opacity = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {
        opacity.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = offsetY.value.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.slogan),
            contentDescription = "Second Image",
            modifier = Modifier

                .then(Modifier.alpha(opacity.value))
        )
    }
}

