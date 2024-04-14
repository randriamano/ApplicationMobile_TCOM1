package com.example.getmarketadmin.screens.home


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_comget.screens.Routes.MainScreens
import com.example.getmarketadmin.BottomNavigationBar
import com.example.getmarketadmin.R
import com.example.getmarketadmin.screens.data.productList
import com.example.getmarketadmin.screens.home.components.CategoryTitle
import com.example.getmarketadmin.screens.home.components.ProductItem
import com.example.getmarketadmin.screens.home.components.header
import com.example.getmarketadmin.ui.theme.Bg
import com.example.getmarketadmin.ui.theme.Bg40
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor
import com.example.getmarketadmin.ui.theme.GrayColor


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
        WindowCompat.getInsetsController(window, context.window.decorView)?.isAppearanceLightStatusBars = !isDarkTheme
    }

    Scaffold(
        topBar = { },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navControllerApp.navigate("add_new_product_route")  },
                containerColor = BgButtonColor,
                contentColor = ButtonColor,
                //shape = CircleShape,
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
                // .padding(start = 20.dp, end = 7.dp)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
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
    }
}

   /* Scaffold(
        topBar = { },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navControllerApp.navigate("add_new_product_route")  },
                containerColor = BgButtonColor,
                contentColor = ButtonColor,
                //shape = CircleShape,
                modifier = Modifier.padding(end = 15.dp , bottom = 15.dp)
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.", Modifier.size(40.dp))
            }
        },

        content = {
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
    )
    /*Column(
                modifier = Modifier
                    .background(Bg40)
                    .fillMaxSize()
                    .fillMaxWidth()
               //     .padding(start = 20.dp, end = 5.dp)
            ) {
                header()
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(productList.size) { index ->
                        ProductItem(index = index, navControllerApp)
                    }
               */
}*/




@Preview(showBackground = true)
@Composable
fun HomePreview(){
    var navControllerApp = rememberNavController()
    HomeScreen(navControllerApp)
}
    
