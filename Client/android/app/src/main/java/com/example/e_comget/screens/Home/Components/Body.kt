package com.example.e_comget.screens.Home.Components


import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.e_comget.Datoum.model.Product
import com.example.e_comget.Datoum.model.ProductDetail
import com.example.e_comget.GlobalViewModel
//import com.example.e_comget.Datoum.model.productList
import com.example.e_comget.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

//TO DO
//Adding sign in button at the bottom of the product (tsy maika)
//Fetching all products details by product Id before navigating into the ProductDetails

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProductSection(navController: NavController
                   , productList: List<ProductDetail>
){
    Column {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            text = "Explorer")
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        ) {
            items(productList.size) {
                index -> ProductItem(productList[index], navController)
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


@Composable
@Preview
fun ProductPreview(){

}