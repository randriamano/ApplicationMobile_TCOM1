package com.example.e_comget.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//Create a gradient Color
fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}