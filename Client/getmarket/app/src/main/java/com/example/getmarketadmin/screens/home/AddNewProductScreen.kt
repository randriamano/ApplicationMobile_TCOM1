package com.example.getmarketadmin.screens.home

import android.app.Activity
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.getmarketadmin.R
import com.example.getmarketadmin.screens.data.ColorItem
import com.example.getmarketadmin.screens.home.components.ButtonComponent
import com.example.getmarketadmin.screens.home.components.CategoryDropdownComponent
import com.example.getmarketadmin.screens.home.components.MultipleTextFieldComponent
import com.example.getmarketadmin.screens.home.components.NumberFieldComponent
import com.example.getmarketadmin.screens.home.components.SizeSelectionComponent
import com.example.getmarketadmin.screens.home.components.TextFieldComponent
import com.example.getmarketadmin.screens.home.components.colorPiquer.ColorPickerDialog
import com.example.getmarketadmin.screens.home.components.colorPiquer.toColor
import com.example.getmarketadmin.screens.home.components.imagePicker.ImagePickerViewModel
import com.example.getmarketadmin.screens.home.components.imagePicker.MyScreen
import com.example.getmarketadmin.screens.home.components.imagePicker.pickImage
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor
import com.example.getmarketadmin.screens.home.ColorItemContent as ColorItemContent
import androidx.activity.compose.setContent
import androidx.activity.viewModels

//TODO
// Add upload photo option

@Composable
fun AddNewProductScreen(navControllerApp: NavHostController){

    val context = LocalContext.current
    val background = Color.White
    val isDarkTheme = isSystemInDarkTheme()


    SideEffect {
        val window = (context as Activity).window
        window.statusBarColor = background.toArgb()
        WindowCompat.getInsetsController(window, context.window.decorView)?.isAppearanceLightStatusBars = !isDarkTheme
    }
    Column (
        modifier = Modifier
            .background(background)
    ){
        AddMedicationScreen(navControllerApp)
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicationScreen(navControllerApp: NavHostController) {

    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        //---------------HEAD------------------------

        headerAddNewProduct(navControllerApp)

        Spacer(modifier = Modifier.height(8.dp))

        //----------------IMAGES------------------------

       // val viewModel: ImagePickerViewModel by viewModels()
       // pickImage(viewModel = viewModel)

        //-----------------NAME----------------------------

        var productName by rememberSaveable { mutableStateOf("") }

        TextFieldComponent(
            productName = productName,
            label = "Nom produit",
            onProductNameChange = { productName = it },
            placeholderText = "e.g. T-shirt"
        )

        //Spacer(modifier = Modifier.padding(4.dp))

        //-----------------PRIX--------------------------------

        var prix by rememberSaveable { mutableStateOf("0") }

        NumberFieldComponent(
            numberOfDosage = prix,
            onNumberOfDosageChange = { prix = it },
            label = "Prix",
            placeholderText = "e.g. 1",
            suffix = "Ariary"
        )

        //---------------------DESCRIPTION---------------------------
        var descripton by rememberSaveable { mutableStateOf("") }

        MultipleTextFieldComponent(
            productName = descripton,
            label = "Descriptions",
            onProductNameChange = { descripton = it },
            placeholderText = "e.g. T-shirt personnalisé"
        )
        //Spacer(modifier = Modifier.padding(4.dp))


        //--------------------STOCKE-----------------------------

        var numberOfDosage by rememberSaveable { mutableStateOf("1") }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //-----------------Stocks--------------------------------

            NumberFieldComponent(
                numberOfDosage = numberOfDosage,
                onNumberOfDosageChange = { numberOfDosage = it },
                label = "Nombre de stockes",
                placeholderText = "e.g. 1",
                suffix = ""
            )

            //---------------------------CATEGORY-----------------------


            val options = listOf(
                "Vêtements",
                "Goodies",
                "Billets",
            )

            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }

            CategoryDropdownComponent(
                selectedOptionText = selectedOptionText,
                onSelectionChange = { selectedOptionText = it },
                options = options,
                expanded = expanded,
                onExpandedChange = { expanded = it }
            )


        }

        //-------------------SIZE------------------------

        var isSSelected by rememberSaveable { mutableStateOf(false) }
        var isMSelected by rememberSaveable { mutableStateOf(false) }
        var isLSelected by rememberSaveable { mutableStateOf(false) }
        var isXLSelected by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
               // .padding(16.dp, 16.dp),
        ) {
            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = "Taille: ",
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            ){
                SizeSelectionComponent(
                    sizeList = listOf("S", "M", "L", "XL"),
                    selectedSizes = listOf(isSSelected, isMSelected, isLSelected, isXLSelected),
                    onSizeSelected = { index ->
                        when (index) {
                            0 -> isSSelected = !isSSelected
                            1 -> isMSelected = !isMSelected
                            2 -> isLSelected = !isLSelected
                            3 -> isXLSelected = !isXLSelected
                        }
                    }
                )
            }


        }

        //-----------COLOR PICKER---------------------------
        var show by remember { mutableStateOf(false) }
        var color by remember { mutableStateOf("#000000") }
        var colorName by rememberSaveable { mutableStateOf("Noir") }
        var colorItemList by remember { mutableStateOf(listOf<ColorItem>()) }


        fun addColorItem(pickedColor: String, pickedColorName: String) {
            colorItemList = colorItemList + ColorItem(pickedColor, pickedColorName)
        }

        fun removeColorItem(colorItem: ColorItem) {
            colorItemList = colorItemList - colorItem
        }


        if (show) {
            ColorPickerDialog(
                initialColor = "#AAAAAA",
                colors = listOf("#FF0000", "#00FF00", "#0000FF"),
                onChoice = { pickedColor, pickedColorName ->
                    Log.d("", "Color selected $pickedColor")
                    color = pickedColor
                    colorName = pickedColorName
                    addColorItem(pickedColor, pickedColorName)
                    Log.d("", "colorItemList: $colorItemList")
                    show = false
                },
            )
        }
        Text(
            text = "Couleur: ",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
        ) {

            FloatingActionButton(
                onClick = {  show = true },
                containerColor = BgButtonColor,
                contentColor = ButtonColor,
                modifier = Modifier.padding(end = 15.dp , bottom = 15.dp)
            ) {
                Icon(Icons.Filled.Add, "Small floating action button.", Modifier.size(30.dp))
            }


            LazyRow{
                itemsIndexed(colorItemList){index, colorItem ->
                    ColorItemContent(colorItem = colorItem, onRemoveColorItem = { removeColorItem(it) })
                }
            }



        }



        //---------------------SAVE-----------------------------------

        Spacer(modifier = Modifier.padding(8.dp))

        //add onClick parameter
        ButtonComponent(label = "Ajouter", enable= true)

    }
}


@Composable
fun ColorItemContent(colorItem: ColorItem, onRemoveColorItem: (ColorItem) -> Unit) {
    Column(
        modifier = Modifier
            .padding(end = 8.dp)
            .clickable {
                onRemoveColorItem(colorItem)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(55.dp)
                .background(colorItem.colorItemCode.toColor(Color.White))
        )
        Text(
            text = colorItem.colorItemName,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun headerAddNewProduct(navControllerApp: NavHostController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
          //  .padding(start = 5.dp, end = 5.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {



        Row(
            Modifier
                .weight(0.5f),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(onClick = { navControllerApp.navigateUp() }) {
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black
                )
            }
        }
        Row(
            Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Nouveau produit",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }



    }

}



