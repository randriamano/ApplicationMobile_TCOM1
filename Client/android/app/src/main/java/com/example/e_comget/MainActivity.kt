package com.example.e_comget

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_comget.Datoum.model.ProductDetail
import com.example.e_comget.Datoum.model.UIStateName
import com.example.e_comget.Datoum.model.productList
import com.example.e_comget.ui.theme.EcomGETTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcomGETTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier
                        .fillMaxSize()
                    , color = MaterialTheme.colorScheme.background) {
                        App()
                }
            }
        }
    }
}

@Composable
fun GreetingPreview() {
    EcomGETTheme {
//        App()
    }
}