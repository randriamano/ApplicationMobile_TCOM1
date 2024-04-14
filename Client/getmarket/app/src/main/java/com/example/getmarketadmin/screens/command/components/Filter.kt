package com.example.getmarketadmin.screens.command.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getmarketadmin.screens.data.filterList
import com.example.getmarketadmin.ui.theme.Bg40
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor
import com.example.getmarketadmin.ui.theme.Primary
import com.example.getmarketadmin.ui.theme.Secondary

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
        //shape = RoundedCornerShape(24.dp),
      //  elevation = 4.dp,
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 5.dp, bottom = 10.dp)
    ) {

    Row(
        modifier = Modifier
           // .clip(shape = RoundedCornerShape(24.dp))
            .background(Bg40)
    ) {
        options.forEach { text ->
            Row(
               /* modifier = Modifier
                    .padding(
                        all = 8.dp,
                    ),*/
            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        /*.clip(
                            shape = RoundedCornerShape(
                                size = 12.dp,
                            ),
                        )*/
                        .clickable {
                            onSelectionChange(text)
                        }
                        .background(
                            if (text == selectedOption) {
                                BgButtonColor
                            } else {
                                Color.Transparent
                            }
                        )
                        /*.border(
                            width = 2.dp,
                            color = if (text == selectedOption) ButtonColor else Color.Transparent,
                        )*/
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

