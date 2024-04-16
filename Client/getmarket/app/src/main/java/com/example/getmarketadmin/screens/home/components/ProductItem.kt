package com.example.getmarketadmin.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.getmarketadmin.screens.data.Product
import com.example.getmarketadmin.screens.data.productList

@Composable
fun ProductItem(index : Int, navControllerApp: NavHostController){

    var product: Product = productList[index];
    var context = LocalContext.current;
    val imageResId = context.resources.getIdentifier(product.productImage, "drawable", context.packageName)

    var image = painterResource(id = imageResId)
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(end = 15.dp, bottom = 10.dp),
            //.clickable {  },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            Modifier
                .clickable {
                    navControllerApp.navigate("product_details_route")
                    //TODO
                    // Match with the appropriate index selected
                }

        ){
            Image(
                painter = image,
                contentDescription = "product",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(165.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.padding(
                    top = 0.dp,
                    end = 16.dp,
                    start = 16.dp,
                    bottom = 10.dp
                )
            ) {

                Text(
                    text = product.productName,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp
                )

                Row {

                    Text(
                        text = "Ar ${product.productPrice}",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 17.sp
                    )
                }


            }
        }

    }

}
