package com.example.e_comget

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.e_comget.ui.theme.EcomGETTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val globalViewModel: GlobalViewModel by viewModels()


        setContent {
            EcomGETTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier
                        .fillMaxSize()
                    , color = MaterialTheme.colorScheme.background) {
                    Log.d("", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv${globalViewModel}")
                        App()
                }
            }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun onBackPressed() {
//        // on below line we are checking if the press time is greater than 2 sec
//        if (pressedTime + 2000 > System.currentTimeMillis()) {
//            // if time is greater than 2 sec we are closing the application.
//            super.onBackPressed()
//            finish()
//        } else {
//            // in else condition displaying a toast message.
//            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
//        }
//        // on below line initializing our press time variable
//        pressedTime = System.currentTimeMillis();
//    }
}


@Composable
fun GreetingPreview() {
    EcomGETTheme {
//        App()
    }
}