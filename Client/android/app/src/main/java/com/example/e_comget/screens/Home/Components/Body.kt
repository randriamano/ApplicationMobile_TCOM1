package com.example.e_comget.screens.Home.Components


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
import androidx.navigation.NavController
import com.example.e_comget.screens.data.Product
import com.example.e_comget.screens.data.productList

//TO DO
//Adding sign in button at the bottom of the product (tsy maika)
//Fetching all products details by product Id before navigating into the ProductDetails

@Composable
fun ProductSection(navController: NavController){
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
                index -> ProductItem(index = index, navController)
            }
        }
    }
}

@Composable
fun ProductItem(index : Int, navController: NavController){
    var product: Product = productList[index];
    var context = LocalContext.current;
    val imageResId = context.resources.getIdentifier(product.productImage, "drawable", context.packageName)

    var image = painterResource(id = imageResId)
    Box(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .clickable { navController.navigate("product_details_route") },
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