package com.example.e_comget.screens.Home.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_comget.Datoum.model.item.Category
import com.example.e_comget.Datoum.model.item.categoryList
import com.example.e_comget.MainViewModel
import com.example.e_comget.ui.theme.Primary
import com.example.e_comget.ui.theme.Secondary

//TODO
//Adding logic to fetch product based on category names

@Composable
fun CategorySection(mainViewModel: MainViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier.background(Color.White)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    text = "Categories"
                )
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = "Forward",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            CategoryItems(categoriesLists = categoryList, mainViewModel = mainViewModel)
        }
    }
}

@Composable
fun CategoryItems(categoriesLists: List<Category>, mainViewModel: MainViewModel) {
    LazyRow {
        itemsIndexed(categoriesLists) { index, item ->
            CategoryItem(data = item, onClick = {
                mainViewModel.reinitializeProducts()

                if (item.categoryEndPointName.equals("")) mainViewModel.getProduct()
                else mainViewModel.getProductsByCategoryName(item.categoryEndPointName)
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(data: Category, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .heightIn(55.dp)
            .widthIn(190.dp)
            .padding(end = 10.dp)
            .border(2.dp, Color.White, shape = RoundedCornerShape(11.dp)),
        onClick = { onClick() }
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary))
                )
                .size(height = 55.dp, width = 190.dp)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 17.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                text = "${data.categoryName}"
            )
        }
    }
}

@Composable
@Preview
fun CategoryPreview() {
    CategoryItem(Category(1, "Tous les categories"), onClick = {})
}