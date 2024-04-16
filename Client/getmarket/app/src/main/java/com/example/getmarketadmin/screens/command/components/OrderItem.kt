package com.example.getmarketadmin.screens.command.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.getmarketadmin.screens.data.Order
import com.example.getmarketadmin.screens.data.orderList
import com.example.getmarketadmin.ui.theme.ButtonColor

@Composable
fun OrderItem(index : Int, navController: NavController){

   var order: Order = orderList[index];

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            Modifier
                .padding(
                    top = 20.dp,
                    end = 16.dp,
                    start = 20.dp,
                    bottom = 20.dp
                )

        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = order.orderName, //order's name
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp
                )
                Text(text = order.orderClass, color = Color.Black, fontSize = 16.sp) // order's class 001-M1
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${order.orderProduct} (${order.orderNumber})", // product's name
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row {

                Text(
                    text = "Couleur: ${order.orderColor}",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive
                )

                Spacer(modifier = Modifier.width(20.dp))

                if(order.orderProductCategory == "Vêtements"){

                    Text(
                        text = "Taille: ${order.orderSize}",
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Cursive
                    )
                }

            }

            Spacer(modifier = Modifier.height(15.dp))

            if (!(order.isDeliver)){
                Button(onClick = {
                                 //TODO
                                 // change order.isDeliver to true
                },
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                ) {
                    Text("Livré")
                }
            }
        }
    }

}