package com.example.getmarketadmin.screens.profile

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.getmarketadmin.R
import com.example.getmarketadmin.screens.home.components.ButtonComponent
import com.example.getmarketadmin.ui.theme.ButtonColor


@Composable
fun LoginScreen(navControllerApp: NavHostController){

    var adminCardNum by rememberSaveable { mutableStateOf("") }
    var adminPassword by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val background = Color.White
    val isDarkTheme = isSystemInDarkTheme()


    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView).isAppearanceLightStatusBars = !isDarkTheme
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 28.dp, end = 28.dp, top = 50.dp),
        ) {
            HeadingTextComponent("Se connecter")

            Spacer(modifier = Modifier.height(80.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Numéro étudiant (001-M1)") },
                value = adminCardNum,
                onValueChange = { adminCardNum = it },
                singleLine = true,
                colors =
                TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = ButtonColor,
                    focusedIndicatorColor = ButtonColor,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = ButtonColor,
                    cursorColor = ButtonColor,

                    ),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.id_card_24px), contentDescription = "")
                },
            )


            PasswordTextFieldComponent(
                password = adminPassword,
                labelValue = "Mot de passe",
                painterResource = painterResource(id = R.drawable.lock_24px),
                onChange = { adminPassword = it})

            Spacer(modifier = Modifier.height(50.dp))

            ButtonComponent(
                label = "Connexion",
                enable = true,
                onClick = {
                    navControllerApp.navigate("bottomNavigation")
                })
            Spacer(modifier = Modifier.height(10.dp))


        }

    }

}


@Composable
fun PasswordTextFieldComponent(password: String, labelValue: String, painterResource: Painter, onChange: (String) -> Unit){


    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors =
        TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = ButtonColor,
            focusedIndicatorColor = ButtonColor,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = ButtonColor,
            cursorColor = ButtonColor,

            ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password ,
        onValueChange = onChange,
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value){
                "Hide password"
            } else {
                "Show password"
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value}) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()

    )

}

@Preview
@Composable
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LoginScreen(navControllerApp = navController)
}
