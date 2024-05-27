package com.example.e_comget.screens.Home


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_comget.DataStoreViewModel
import com.example.e_comget.Datoum.model.UIState
import com.example.e_comget.MainViewModel
import com.example.e_comget.screens.Home.Components.CategorySection
import com.example.e_comget.screens.Home.Components.Header
import com.example.e_comget.screens.Home.Components.ProductSection
import kotlinx.coroutines.flow.last

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navControllerApp: NavHostController,
    uiState: UIState, onGetProduct: () -> Unit, mainViewModel: MainViewModel
) {
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()

    val context = LocalContext.current
    val background = Color.White
    val isDarkTheme = isSystemInDarkTheme()

    var temp = 0

    LaunchedEffect(Unit) {
        dataStoreViewModel.userId.collect { value ->
            temp += value
        }
    }

    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView).isAppearanceLightStatusBars = !isDarkTheme
    }

    Column (
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 0.dp)
            .background(background)
    ){

//        if(dataStoreViewModel.isLoggedIn.collectAsState(initial = true).value){
//            Text(text = "${temp}")
//        }
        
        Header(navControllerApp)
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        CategorySection(mainViewModel = mainViewModel)
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        ProductSection(
            navControllerApp,
            productList = mainViewModel.productList.value,
            uiState = uiState,
            mainViewModel = mainViewModel
        )
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
}