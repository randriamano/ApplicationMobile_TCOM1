package com.example.e_comget.screens.Home.Components

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.example.e_comget.Datoum.model.item.product
import com.example.e_comget.ui.theme.Bg40
import com.example.e_comget.ui.theme.BgButtonColor
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.e_comget.Datoum.model.UIState
import com.example.e_comget.Datoum.model.item.ProductDetail
import com.example.e_comget.MainViewModel
import com.example.e_comget.R
import com.example.e_comget.Routes.ProductScreens
import com.example.e_comget.ui.theme.Bg60
import com.example.e_comget.ui.theme.Bg80
import com.example.e_comget.ui.theme.Primary


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    navControllerApp: NavHostController,
    uiState: UIState,
    mainViewModel: MainViewModel
) {
    var text by rememberSaveable { mutableStateOf("") } //text to search
    var active by rememberSaveable { mutableStateOf(false) }


    val context = LocalContext.current
    val background = Color.White
    val isDarkTheme = isSystemInDarkTheme()

//    SideEffect {
//        val window = (context as Activity).window
//        window.statusBarColor = background.toArgb()
//        WindowCompat.getInsetsController(window, context.window.decorView).isAppearanceLightStatusBars = !isDarkTheme
//    }

    Column (
        Modifier
            .background(background)
            .fillMaxHeight(1f)
            .padding(bottom = 25.dp)
//            .background(Color.Red)
    ){
        Row(
            Modifier
                .padding(start = 5.dp, end= 5.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Box(modifier = Modifier
                .padding(top= 9.dp)
            ){
                IconButton(onClick = {
                    navControllerApp.navigateUp()
                    /*TODO*/
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = "back home",
                        tint = Color.Black,
                    )
                }
            }

            Box(
                Modifier
//                    .fillMaxSize()
                    .fillMaxWidth()
                    .semantics { isTraversalGroup = true }
                    .background(background)
                    .padding(end = 5.dp)
            ) {
                DockedSearchBar(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 8.dp)
                        .semantics { traversalIndex = -1f },
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = {
                        if(text.isNotEmpty()) mainViewModel.searchProducts(text)
                        active = false
                               },
                    active = active,
                    onActiveChange = { active = it },
                    placeholder = { Text("Chercher un produit") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
                    colors =  SearchBarDefaults.colors(containerColor = Bg60, dividerColor = Bg80),
                ) {


                    //TODO
                    // Creta some suggestion
//                    ListItem(
//                        headlineContent = { Text(product.productName) },
//                        supportingContent = { Text(text = product.productDescription) },
//                        // leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
//                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
//                        modifier = Modifier
//                            .clickable {
//                                text = product.productName
//                                active = false
//                            }
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 4.dp),
//                    )
                }
            }
        }
        Column (
            modifier = Modifier.fillMaxHeight()
        ){
            if (uiState.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(35.dp),
                    )
                }
            } else if (!uiState.error.isNullOrEmpty()) {
                val imageId = R.drawable.nosignal

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(
                            id = imageId
                        ),
                        contentDescription = "Loss connexion",
                        modifier = Modifier
                            .size(75.dp)
                    )
                    Text(
                        text = "Verifier votre connexion internet",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { mainViewModel.getProduct() },
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        )
                    ) {
                        Text(
                            text = "Recharger",
                            fontSize = 15.sp
                        )
                    }
                }
            } else {
                val productList = mainViewModel.uiStateProductSearched.value.data
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    modifier = Modifier.padding(15.dp)
                ) {
                    items(productList.size) { index ->
                        ProductItem(productList[index], navControllerApp)
                    }
                }
            }
        }
    }

}