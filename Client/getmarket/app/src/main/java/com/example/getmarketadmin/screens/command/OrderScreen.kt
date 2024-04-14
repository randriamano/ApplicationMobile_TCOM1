package com.example.getmarketadmin.screens.command

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.getmarketadmin.screens.command.components.CustomRadioGroup
import com.example.getmarketadmin.screens.command.components.OrderHeader
import com.example.getmarketadmin.screens.command.components.OrderItem
import com.example.getmarketadmin.screens.data.orderList
import com.example.getmarketadmin.ui.theme.Bg40

//TODO
// If the order is not a "vêtements" don't show "Taille"

@Composable
fun OrderScreen(navControllerApp: NavHostController) {

    val context = LocalContext.current
    val background = Bg40
    val isDarkTheme = isSystemInDarkTheme()


    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView)?.isAppearanceLightStatusBars = !isDarkTheme
    }

     Column(
            modifier = Modifier
                .background(Bg40)
                .fillMaxSize()
                .fillMaxWidth()
           //     .padding(start = 20.dp, end = 5.dp)
    ) {
        OrderHeader()
         CustomRadioGroup()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(orderList.size) { index ->
                    OrderItem(index = index, navControllerApp)
                }
            }
     }


}



@Preview(showBackground = true)
@Composable
fun HomePreview(){
    var navControllerApp = rememberNavController()
    OrderScreen(navControllerApp)
}

