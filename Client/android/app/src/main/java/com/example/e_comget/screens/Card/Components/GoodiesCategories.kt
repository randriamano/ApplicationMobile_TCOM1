package com.example.e_comget.screens.Card.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HourglassTop
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.e_comget.R
import com.example.e_comget.Datoum.model.Command
import com.example.e_comget.Datoum.model.command

@Composable
fun GoodiesScreen(navControllerApp: NavHostController){

    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Box{
                Image(painter = painterResource(R.drawable.goodies),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Box (
                    modifier = Modifier
                        .align(Alignment.Center)
                        .border(BorderStroke(1.dp, color = Color.White))
                        .padding(10.dp)
                ) {
                    Text(
                        text = " G o o d i e s  ",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White,
                        )
                    )
                }
            }


            IconButton(onClick = {
                navControllerApp.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Sharp.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )

            }

        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(600.dp),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        ) {
            items(command.size) {
                    index -> CommandGoodiesList(index = index, navControllerApp)
            }
        }
    }
}

@Composable
fun CommandGoodiesList(index : Int,navControllerApp: NavController) {
    var command : Command = command[index]

    // Définir la bordure
    val border = BorderStroke(
        color = Color.LightGray,
        width = 1.dp
    )
    Box(
        modifier = Modifier
            .absolutePadding(13.dp, 5.dp, 13.dp, 5.dp)
            .border(border, shape = RoundedCornerShape(8.dp))

    ) {

        Column (
            modifier = Modifier
                .padding(13.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 6.dp),
                text = "${command.nomProduct}",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif
                )
            )
            Row {
                Text(
                    modifier = Modifier
                        .padding(end = 20.dp, bottom = 9.dp),
                    text = "Couleur: ${command.couleur}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily.Cursive
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 9.dp),
                    text = "Taille: ${command.taille}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily.Cursive
                    )
                )
            }

            Text(
                text = "Description : bla bla déja en ${command.etat}",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            )
            Row (
                modifier = Modifier
                    .padding(1.dp)
            ){

                Text(
                    modifier = Modifier
                        .absolutePadding(top = 7.dp)
                        .weight(1f),
                    text = "Prix : Ar ${command.prix}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light
                    )
                )
                Row (
                    Modifier
                        .absolutePadding(top = 7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Outlined.HourglassTop,
                        contentDescription = "navigation next",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(17.dp)

                    )
                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        text = "En Cours",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Red
                        )
                    )
                }

            }
        }
    }
}