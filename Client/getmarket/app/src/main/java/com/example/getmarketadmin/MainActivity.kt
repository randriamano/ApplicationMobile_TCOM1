package com.example.getmarketadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.getmarketadmin.screens.home.components.imagePicker.ImagePickerViewModel
import com.example.getmarketadmin.screens.home.components.imagePicker.MyScreen
import com.example.getmarketadmin.screens.home.components.imagePicker.pickImage
//import com.example.getmarketadmin.screens.home.components.colorPiquer.ColorPickerScreen

import com.example.getmarketadmin.ui.theme.GetMarketAdminTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ImagePickerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetMarketAdminTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                    val navController = rememberNavController()
                    //ImagePickerViewModel
                    //pickImage()
                    //MyScreen(viewModel = viewModel)



                }
            }
        }
    }
}

