package com.example.getmarketadmin.screens.command.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getmarketadmin.ui.theme.Bg40
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor


@Composable
fun CustomRadioGroup() {
    val options = listOf(
        "Tous",
        "En cours",
        "Livré",
    )
    var selectedOption by remember {
        mutableStateOf(options[0])
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Surface(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 5.dp, bottom = 10.dp)
    ) {

    Row(
        modifier = Modifier

            .background(Bg40)
    ) {
        options.forEach { text ->
            Row{
                Text(
                    text = text,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .clickable {
                            onSelectionChange(text)
                            //TODO
                            //Change the content related to the selected text
                        }
                        .background(
                            if (text == selectedOption) {
                                BgButtonColor
                            } else {
                                Color.Transparent
                            }
                        )

                        .drawBehind {
                            if (text == selectedOption){
                                val borderSize = 4.dp.toPx()
                                drawLine(
                                    color = ButtonColor,
                                    start = Offset(0f, size.height),
                                    end = Offset(size.width, size.height),
                                    strokeWidth = borderSize
                                )
                            }
                        }
                        .padding(
                            vertical = 12.dp,
                            horizontal = 20.dp,
                        ),
                )
            }
        }
    }

    }
}

@Preview(showBackground = true)
@Composable
fun FilterCardPreview(){
    CustomRadioGroup()
}

