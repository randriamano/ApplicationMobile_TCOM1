package com.example.getmarketadmin.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.getmarketadmin.screens.data.productList
import com.example.getmarketadmin.ui.theme.Bg40

@Composable
fun bodySection(navControllerApp: NavHostController){
    Column(
        modifier = Modifier
            .background(Bg40)
            .fillMaxSize()
            .fillMaxWidth()
            .padding(start = 20.dp, end = 7.dp)
    ) {
        header()
        CategoryTitle()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(productList.size) { index ->
                ProductItem(index = index, navControllerApp)
            }
        }
    }
}