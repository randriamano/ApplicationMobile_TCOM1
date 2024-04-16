package com.example.getmarketadmin.screens.home.components.colorPiquer

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.getmarketadmin.screens.data.ColorItem
import com.example.getmarketadmin.ui.theme.ButtonColor


/**
 * Color strings should be formatted as hex color values (#000000)
 *
 **/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerDialog(
    initialColor: String,
    colors: List<ColorItem>,
    onChoice: (String, String) -> Unit,
    noChoice: () -> Unit
) {
    var colorName by rememberSaveable { mutableStateOf("Gris") }
    var color by remember(initialColor) { mutableStateOf(initialColor) }
    var hexTextColor by remember(initialColor) {
        mutableStateOf(initialColor.toColor(Color.White).contrastColor())
    }

    val colorRendered by animateColorAsState(
        targetValue = color.toColor(Color.White),
        label = "color-picker-animation",
        finishedListener = {
            hexTextColor = it.contrastColor()
        }
    )

    val onDismissRequest = {
        if (Patterns.color.matches(color)) {
            onChoice(color, colorName)
        }
    }

    AlertDialog(
        onDismissRequest = noChoice,
    ) {
        Surface(
            shape = AlertDialogDefaults.shape,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(colorRendered)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                ) {
                    Text(
                        text = "#",
                        style = MaterialTheme.typography.headlineSmall,
                        color = hexTextColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )

                    BasicTextField(
                        value = color.substring(1),
                        onValueChange = { newColor ->
                            if (newColor.length <= 6) {
                                color = "#$newColor"
                            }
                            colorName = ""
                        },
                        textStyle = MaterialTheme.typography.headlineSmall
                            .copy(
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                color = hexTextColor
                            ),
                        modifier = Modifier
                            .padding(0.dp)
                            .background(colorRendered)
                           .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 50.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(colors) {
                            Button(
                                onClick = {
                                    color = it.colorItemCode
                                    colorName = it.colorItemName
                                          },
                                shape = CircleShape,
                                modifier = Modifier.requiredSize(50.dp),
                                contentPadding = PaddingValues(1.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(it.colorItemCode.toColorInt())
                                ),
                                border = if (it.colorItemCode == color)
                                    BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface)
                                else
                                    null,
                                content = {}
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                       Text(
                            text = "Nom du couleur :",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(start = 3.dp)
                                .wrapContentSize(Alignment.BottomStart, false),
                            value = colorName,
                            onValueChange = { colorName = it },
                            placeholder = { Text(text = "e.g. Bleu nuit") },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = ButtonColor,
                                focusedIndicatorColor = ButtonColor,
                                unfocusedContainerColor = Color.Transparent,
                            ),
                        )



                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    TextButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.align(Alignment.End),
                        enabled = colorName != ""
                    )
                    {
                        Text(text = "Enregistrer",
                            style = MaterialTheme.typography.labelLarge,
                            color = ButtonColor)
                    }
                }
            }
        }
    }
}

