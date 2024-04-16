package com.example.getmarketadmin.screens.home


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.getmarketadmin.screens.data.productList
import com.example.getmarketadmin.screens.home.components.CategoryTitle
import com.example.getmarketadmin.screens.home.components.ProductItem
import com.example.getmarketadmin.screens.home.components.Header
import com.example.getmarketadmin.ui.theme.Bg40
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor


//TODO
// Search Button
// Filter Button
// Change the second title as the filter option change
// Match each card with the appropriate data


@Composable
fun HomeScreen(navControllerApp: NavHostController) {

    val context = LocalContext.current
    val background = Bg40
    val isDarkTheme = isSystemInDarkTheme()


    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView).isAppearanceLightStatusBars = !isDarkTheme
    }

    Scaffold(
        topBar = { },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navControllerApp.navigate("add_new_product_route")  },
                containerColor = BgButtonColor,
                contentColor = ButtonColor,
                modifier = Modifier.padding(end = 15.dp , bottom = 15.dp)
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.", Modifier.size(30.dp))
            }
        },

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Bg40)
                .fillMaxSize()
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 7.dp)
            ) {
                Header()
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
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview(){
    val navControllerApp = rememberNavController()
    HomeScreen(navControllerApp)
}
    
