package com.example.e_comget

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.e_comget.ui.theme.EcomGETTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var pressedTime: MutableState<Long> =
        mutableStateOf(0)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val globalViewModel: GlobalViewModel by viewModels()
        val dataStoreViewModel: DataStoreViewModel by viewModels()

        setContent {
            EcomGETTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBackPressed() {
        if (pressedTime.value + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(getBaseContext(), "Appuyer encore pour quitter", Toast.LENGTH_SHORT)
                .show();
        }
        pressedTime.value = System.currentTimeMillis();
    }
}


@Composable
fun GreetingPreview() {
    EcomGETTheme {
//        App()
    }
}