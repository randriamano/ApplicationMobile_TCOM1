package com.example.e_comget.screens.Home


import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.e_comget.Datoum.model.ProductDetail
import com.example.e_comget.Datoum.model.UIState
import com.example.e_comget.GlobalViewModel
import com.example.e_comget.MainViewModel
import com.example.e_comget.screens.Home.Components.CategorySection
import com.example.e_comget.screens.Home.Components.ProductSection
import com.example.e_comget.screens.Home.Components.SearchBarSection

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navControllerApp: NavHostController, productList: List<ProductDetail>, uiState: UIState, onGetProduct: () -> Unit, mainViewModel: MainViewModel) {
    val globalViewModel: GlobalViewModel = viewModel()

//    if(productList.size == 0) onGetProduct()
    
    Surface(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 0.dp)){
        Column {
            Text(text = "Value : ${globalViewModel.isSignedIn.value}")
            SearchBarSection()
            Spacer(modifier = Modifier
                .height(15.dp))
            CategorySection()
            Spacer(modifier = Modifier
                .height(25.dp))
            ProductSection(navControllerApp,
                productList  = productList,
                uiState = uiState,
                mainViewModel = mainViewModel
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
}