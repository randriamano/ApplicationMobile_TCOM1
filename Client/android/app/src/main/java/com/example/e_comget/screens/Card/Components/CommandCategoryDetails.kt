package com.example.e_comget.screens.Card.Components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HourglassTop
import androidx.compose.material.icons.outlined.OfflinePin
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.example.e_comget.Datoum.model.item.CommandItem
import com.example.e_comget.Datoum.model.ProductCommandedDetails
import com.example.e_comget.Datoum.model.tempProductCommandedDetails
import com.example.e_comget.MainViewModel
import com.example.e_comget.ui.theme.Primary

@Composable
fun CommandCategoryDetailsScreen(
    navController: NavHostController,
    commandItem: CommandItem = CommandItem(),
    mainViewModel: MainViewModel,
    onGetCommandCategoryProducts: () -> Unit
){

    DisposableEffect(Unit) {
        onDispose {
            onGetCommandCategoryProducts()
        }
    }

    var productCommandedDetailsList: List<ProductCommandedDetails>? = mainViewModel.uiStateProductCommanded.value.data
    val uiState = mainViewModel.uiStateProductCommanded.value

    if(productCommandedDetailsList == null) {
        onGetCommandCategoryProducts()
    }
    else {
        if(uiState.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(35.dp),
            )
        }else if(!uiState.error.isNullOrEmpty()) {
            Text(text = "Error")
            Button(onClick = onGetCommandCategoryProducts) {
                Text(text = "Recharger")
            }
        }else {

            Column{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Box{
                        Image(painter = painterResource(commandItem.productCategoryImageId),
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
                                text = "${commandItem.productCategoryName}",
                                letterSpacing = 13.sp,
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
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }

                }

                if(productCommandedDetailsList.size > 0){
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(600.dp),
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        items(productCommandedDetailsList.size) {
                                index -> CommandListItem(productCommandedDetails = productCommandedDetailsList[index], navController)
                        }
                    }
                }else {
                    Row{
                        Text(text = "${productCommandedDetailsList.size}")
                        Text(text = "${commandItem.productCategoryEndpointName}")
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Pas de produit Commander",
                            color = Color.LightGray,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
//                            onClick = {navController.navigate("bottomNavigation")},
                            onClick = {onGetCommandCategoryProducts},
                            modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary
                            )
                        ) {
                            Text(
                                text = "Chercher des nouveaux produits",
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CommandListItem(productCommandedDetails: ProductCommandedDetails, navController: NavController) {

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
                text = "${productCommandedDetails.productName}",
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
                    text = "Couleur: ${productCommandedDetails.productColorChosen}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily.Cursive
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(bottom = 9.dp),
                    text = "Taille: ${productCommandedDetails.productSizeChosen}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily.Cursive
                    )
                )
            }

            Text(
                text = "Description : ${productCommandedDetails.productDescription}",
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
                    text = "Prix : Ar ${productCommandedDetails.productPrice}",
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
                    if(productCommandedDetails.productIsPaid){
                        Icon(Icons.Outlined.OfflinePin,
                            contentDescription = "navigation next",
                            tint = Color.Red,
                            modifier = Modifier
                                .size(17.dp)

                        )
                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            text = "Payé",
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color.Red
                            )
                        )
                    }else {
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

}
