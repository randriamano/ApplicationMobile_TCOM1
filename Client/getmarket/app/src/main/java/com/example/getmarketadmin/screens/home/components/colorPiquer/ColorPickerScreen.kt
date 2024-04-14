package com.example.getmarketadmin.screens.home.components.colorPiquer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.getmarketadmin.screens.data.ColorItem


/**
 * Color strings should be formatted as hex color values (#000000)
 **/
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerScreen() {
    var show by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf("#000000") }
    var colorName by rememberSaveable { mutableStateOf("Noir") }
    var colorItemList by remember { mutableStateOf(listOf<ColorItem>()) }

    val onColorSelected: (String) -> Unit = { pickedColor ->
        color = pickedColor
    }

    val onColorNameSelected: (String) -> Unit = { pickedColorName ->
        colorName = pickedColorName
    }

    fun addColorItem(pickedColor: String, pickedColorName: String) {
        colorItemList = colorItemList + ColorItem(pickedColor, pickedColorName)
    }

    if (show) {
        ColorPickerDialog(
            initialColor = "#AAAAAA",
            colors = listOf("#FF0000", "#00FF00", "#0000FF"),
            onChoice = { pickedColor, pickedColorName -> // Récupération de la couleur et de son nom
                Log.d("", "Color selected $pickedColor")
                color = pickedColor
                colorName = pickedColorName // Affectation du nom de la couleur
                show = false
            },
            colorCallback = onColorSelected,
            colorNameCallback = onColorNameSelected,
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ColorPicker Demo",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { paddingValues ->
        Row(
            /*verticalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.CenterVertically
            ),*/
            //horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Button(
                onClick = {
                    show = true
                }
            ) {
                Text(
                    text = "Show",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(30.dp)
                    .background(color.toColor(Color.White))
            )
            Text(text = "${color} , ${colorName}")

    }
}
*/
