package com.example.e_comget.screens.MyAccount.SignIn.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_comget.ui.theme.BgColor
import com.example.e_comget.ui.theme.Primary
import com.example.e_comget.ui.theme.Secondary
import com.example.e_comget.ui.theme.TextColor
import com.example.e_comget.ui.theme.componentShapes

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}


@Composable
fun MyTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextSelected: (String) -> Unit
) {

    val textValue = remember {
        mutableStateOf("")
    }

    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 8.dp)
                .background(
                    BgColor,

                    shape = RoundedCornerShape(4.dp)
                )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small),
            label = { Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
            ),
            keyboardOptions = KeyboardOptions.Default,
            value = textValue.value,
//            value = textValue,
            onValueChange = {
                textValue.value = it
                onTextSelected(it)
//                textValue = it
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = "profile")
            }

        )
    }


}


@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit
) {

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }


    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 8.dp)
                .background(
                    BgColor,
                    shape = RoundedCornerShape(4.dp)
                )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small),
            label = { Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,

                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = password.value,
            onValueChange = {
                password.value = it
                onTextSelected(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = "")
            },
            trailingIcon = {
                val iconImage = if (passwordVisible.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                val description = if (passwordVisible.value) {
                    "Hide password"
                } else {
                    "Show password"
                }

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()

        )
    }

}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Vous-avez déjà un compte? " else "Vous n'avez pas de compte? "
    val loginText = if (tryingToLogin) "Se connecter" else "Créer un compte"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableLoginTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }

                }

        })
}

@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit) {
    Button(
        onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .clickable { },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}