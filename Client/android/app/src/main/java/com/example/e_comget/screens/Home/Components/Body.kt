package com.example.e_comget.screens.Home.Components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.e_comget.Datoum.model.ProductDetail
import com.example.e_comget.Datoum.model.UIState
import com.example.e_comget.GlobalViewModel
import com.example.e_comget.MainViewModel
import com.example.e_comget.R
import com.example.e_comget.ui.theme.Primary

//TO DO
//Fetching all products details by product Id before navigating into the ProductDetails

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProductSection(navController: NavController
                   , productList: List<ProductDetail>,
                   uiState: UIState,
                   mainViewModel: MainViewModel
){
    Column (
        modifier = Modifier
            .fillMaxHeight()
    ){
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            text = "Explorer")
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
        ){
            if (uiState.isLoading){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(35.dp),
                    )
                }
            }else if (!uiState.error.isNullOrEmpty()){
                val imageId = R.drawable.nosignal

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(painter = painterResource(
                        id = imageId),
                        contentDescription = "Loss connexion",
                        modifier = Modifier
                            .size(75.dp)
                    )
                    Text(
                        text = "Verifier votre connexion internet",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {mainViewModel.getProduct()},
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        )
                    ) {
                        Text(
                            text = "Recharger",
                            fontSize = 15.sp
                        )
                    }
                }
            }else{
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    modifier = Modifier
                ) {
                    items(productList.size) {
                            index -> ProductItem(productList[index], navController)
                    }
                }
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun ProductItem(product: ProductDetail, navController: NavController){
    var context = LocalContext.current;
    val globalViewModel: GlobalViewModel = viewModel()
    val apiURL = globalViewModel.apiUrl.toString()
    var image = rememberAsyncImagePainter(apiURL + product.productImageURLList[0])

    Box(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .clickable {
                navController.navigate("product_details_route/${product.productId}")
            },
    ){
        Column(
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
        ) {
            Image(
                painter = image,
                contentDescription = "product",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(165.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Text(
                    text = "${product.productName}",
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp
                )
                Text(
                    text = "Ar ${product.productPrice}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun ProductPreview(){
    Button(
        onClick = {},
        modifier = Modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary
        )
    ) {
        Text(text = "Recharger")
    }
}