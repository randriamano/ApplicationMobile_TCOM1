package com.example.e_comget.screens.Card.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.e_comget.Datoum.model.item.CommandItem

@Composable
fun BodyListSection(navControllerApp : NavHostController){
    CategoryBodySection(navControllerApp)
}

@Composable
fun CategoryBodySection(navControllerApp: NavHostController){
    val categoryItemList: List<CommandItem> = CommandItem().getCommandItems()
    
    Column {
        Text(
            modifier = Modifier
                .absolutePadding(12.dp,0.dp, 0.dp, 0.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            text = "Categories")
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(categoryItemList.size){
                index ->
                    CategoryItem(navControllerApp = navControllerApp, commandItem = categoryItemList[index])
            }
        }
    }
}

@Composable
fun CategoryItem(navControllerApp: NavHostController, commandItem: CommandItem){
    val border = BorderStroke(
        color = Color.LightGray,
        width = 1.dp
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { navControllerApp.navigate("commandDetails/" + commandItem.productCategoryNavigationRoute) }
            .border(border, shape = RoundedCornerShape(8.dp))
    ){
        Row(
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
        ) {
            Image(
                painter = painterResource(commandItem.productCategoryImageId),
                contentDescription = commandItem.productCategoryName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(165.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Text(
                    modifier = Modifier
                        .padding(15.dp,10.dp),
                    text = commandItem.productCategoryName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier
                        .padding(15.dp,10.dp),
                    text = "Nombre de commande: ${commandItem.productCategoryCommandedCount}",
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp
                )
            }
        }
    }
}

