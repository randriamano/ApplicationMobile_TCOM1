package com.example.e_comget.screens.Home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_comget.screens.Home.Components.ProductSection
import com.example.e_comget.screens.Home.Components.CategorySection
import com.example.e_comget.screens.Home.Components.SearchBarSection

@Composable
fun HomeScreen(navControllerApp: NavHostController) {
    Surface(modifier = Modifier.padding(10.dp, top = 0.dp)){
        Column {
            SearchBarSection()
            Spacer(modifier = Modifier
                .height(15.dp))
            CategorySection()
            Spacer(modifier = Modifier
                .height(25.dp))
            ProductSection(navControllerApp)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
}