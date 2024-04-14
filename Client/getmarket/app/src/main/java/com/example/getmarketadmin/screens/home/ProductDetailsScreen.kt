package com.example.e_comget.screens.Home.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.getmarketadmin.R
import com.example.getmarketadmin.screens.data.product
import com.example.getmarketadmin.screens.home.components.ButtonComponent
import com.example.getmarketadmin.screens.home.components.colorPiquer.toColor
import com.example.getmarketadmin.ui.theme.ButtonColor
import com.example.getmarketadmin.ui.theme.PoloColorBlack
import com.example.getmarketadmin.ui.theme.PoloColorGray
import com.example.getmarketadmin.ui.theme.PoloColorNavyBlue
import com.example.getmarketadmin.ui.theme.PoloColorRed
import com.example.getmarketadmin.ui.theme.PoloColorWhite
import com.example.getmarketadmin.ui.theme.VeryLightGray

//TODO
// Remove Number, it's only for order
// replace "Continuer" button by "edit" and "remove" product
//Create an object to store all data selected by user to send to the next screen (ProductOrderScreen)
//  If not signined : redirect into sign in screen before productOrder
//Logic to enable the "Continue" button when all data are complete

@Composable
fun ProductDetailsScreen(
    navControllerApp: NavHostController,
    ){
    Column (
        verticalArrangement = Arrangement.SpaceBetween
    ){
        DetailsTopBarSection(navControllerApp)
        DetailsBodySection()
        DetailsFooterSection(navControllerApp);
    }
}

@Composable
fun DetailsTopBarSection(navControllerApp: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.White)
    ){
        ImageCarousel(images = product.productImageURLList)
        DetailHeader(navControllerApp = navControllerApp)
    }
}

@Composable
fun DetailHeader(navControllerApp: NavHostController){
   /* IconButton(onClick = {navControllerApp.popBackStack()},
        enabled = true,
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "back",
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }*/
    IconButton(onClick = { navControllerApp.navigateUp() }) {
        Icon(
            Icons.Outlined.ArrowBack,
            contentDescription = "back",
            tint = Color.Black
        )
    }
}

@Composable
fun ImageCarousel(images: List<String>) {
    var currentIndex by remember { mutableStateOf(0) }
    var context = LocalContext.current;
    var imageId = context.resources.getIdentifier(images[currentIndex], "drawable", context.packageName);
    var painter = painterResource(id = imageId);


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f)
    ) {
       Box (
           modifier = Modifier
               .fillMaxWidth()
       ){

           Image(
               painter = painter,
               contentDescription = null,
               contentScale = ContentScale.FillBounds,
               modifier = Modifier
                   .fillMaxWidth()
                   .fillMaxHeight()
           )
           Row(
               modifier = Modifier
                   .fillMaxWidth(1f)
                   .padding(start = 8.dp, end = 8.dp)
                   .align(Alignment.Center),
               Arrangement.SpaceBetween
           ){
               IconButton(
                   onClick = { if (currentIndex > 0) currentIndex-- },
                   enabled = currentIndex >= 0
               ) {
                   Box(
                       modifier = Modifier
                           .size(30.dp)
                           .background(Color.LightGray, shape = RoundedCornerShape(25.dp)),
                       contentAlignment = Alignment.Center
                   ){
                       Icon(
                           imageVector = Icons.Rounded.KeyboardArrowLeft,
                           contentDescription = "back",
                           tint = MaterialTheme.colorScheme.onSecondaryContainer,
                           modifier = Modifier
                               .size(20.dp)
                       )
                   }
               }

               IconButton(
                   onClick = { if (currentIndex < images.size - 1) currentIndex++ },
                   enabled = currentIndex < images.size - 1,
               ) {
                   Box(
                       modifier = Modifier
                           .size(30.dp)
                           .background(Color.LightGray, shape = RoundedCornerShape(25.dp)),
                       contentAlignment = Alignment.Center
                   ){
                       Icon(
                           imageVector = Icons.Rounded.KeyboardArrowRight,
                           contentDescription = "next",
                           tint = MaterialTheme.colorScheme.onSecondaryContainer,
                           modifier = Modifier
                               .size(20.dp)
                       )
                   }
               }
           }
           Column(
               modifier = Modifier
                   .size(width = 60.dp, height = 25.dp)
                   .align(Alignment.BottomCenter)
                   .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
           ){
               Box(
                   modifier = Modifier
                       .size(width = 60.dp, height = 25.dp),
                   contentAlignment = Alignment.Center
               ){
                   Text(
                       fontWeight = FontWeight.Bold,
                       color = Color.Black,
                       text = "${currentIndex + 1} / ${images.size}"
                   )
               }
           }

       }
   }

}

@Composable
fun DetailsBodySection(){
    var scrollState = rememberScrollState();
    var selectedColorIndex by remember {
        mutableStateOf(0)
    }

    var selectedSizeIndex  by remember {
        mutableStateOf(0)
    }

    var numberSelected by remember { mutableStateOf(0) }

    fun changeSelectedColorIndex(newVal : Int){
        selectedColorIndex = newVal;
    }

    fun changeSelectedSizeIndex(newSize : Int){
        selectedSizeIndex = newSize;
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(VeryLightGray)
            .fillMaxHeight(0.85f)
            .padding(10.dp)
    ){
        Column(
           // verticalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Text(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                text = "${product.productName}"
            )

            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                text = "${product.productDescription}"
            )

            Text(
                text = "${product.productPrice} Ar",
                fontSize = 35.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
        Spacer(Modifier.height(7.dp))
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth(1f),
            Arrangement.SpaceBetween
        ){

            Spacer(modifier = Modifier.height(15.dp))
            if (product.availableColorList!!.size > 0){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        ,
                    Arrangement.SpaceBetween
                ){
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                       // text = "Color : ${selectedColorIndex + 1}. ${product.availableColorList[selectedColorIndex]}",
                        text = "Couleur : ${product.availableColorList[selectedColorIndex]}",
                    )
                    Spacer(modifier = Modifier.height(7.dp))

                    ColorItems(
                        product.availableColorList
                        , selectedColorIndex,
                        changeTheSelectedColorIndex = ::changeSelectedColorIndex
                    )
                }
            }
            if(product.availableSizeList!!.size > 0){
                Spacer(modifier = Modifier.height(20.dp))
                Column {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        text = "Taille : ${product.availableSizeList[selectedSizeIndex]}",
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    SizeItems(
                        availableSizeList = product.availableSizeList,
                        selectedIndex = selectedSizeIndex,
                        changeTheSelectedSizeIndex = ::changeSelectedSizeIndex
                    )
                }
            }
            if(product.productRemainingStock!!.compareTo(0) > 0){
                Spacer(modifier = Modifier.height(20.dp))
                Column {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        text = "Nombre : ${numberSelected}",
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    NumberCounter(onNumberChanged = { updatedNumber ->
                        numberSelected = updatedNumber
                    })

                }
            }
        }
    }
}


@Composable
fun NumberCounter(onNumberChanged: (Int) -> Unit) {
    var number by remember { mutableStateOf(0) }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .size(30.dp)
                    .background(ButtonColor)
                    .clickable(enabled = number > 0) {
                        number--
                        onNumberChanged(number)
                    },
                contentAlignment = Alignment.Center,
            ){
                Text(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    text = "-",
                )
            }
            Text(
                text = "$number",
                fontSize = 20.sp,
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .size(30.dp)
                    .background(ButtonColor)
                    .clickable(enabled = number < product.productRemainingStock!!) {
                        number++
                        onNumberChanged(number)
                    },
                contentAlignment = Alignment.Center
            ){
                Text(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    text = "+",
                )
            }
        }


}


@Composable
fun ColorItems(availableColorList: List<String>, selectedIndex: Int, changeTheSelectedColorIndex: (Int) -> Unit){


    LazyRow(
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        itemsIndexed(availableColorList) { index, colorName ->

            AvailableColorItem(
                index = index,
                indexSelected = selectedIndex,
                colorName = colorName,
                onClick = {newIndex -> changeTheSelectedColorIndex(newIndex)}
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableColorItem(index: Int, indexSelected: Int, colorName: String, onClick: (Int) -> Unit){
    val mapColor: MutableMap<String, Color> = hashMapOf(
        "Black" to PoloColorBlack,
        "Navy Blue" to PoloColorNavyBlue,
        "Red" to PoloColorRed,
        "Gray" to PoloColorGray,
        "White" to PoloColorWhite
    )

    var textColor: Color = if(colorName.equals("White")) Color.Black else Color.White;
    var borderColor: Color = Color.Transparent;
    var nullableColor = mapColor[colorName];
    var backgroundColor = nullableColor ?: Color.Black;

    if(index == indexSelected){
        borderColor = if(colorName.equals("White")) Color.Black else Color.White;
    }

    var padding = if(index == 0) 0.dp else 10.dp

    Column (
        modifier = Modifier
            .padding(start = padding)
            .size(50.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
            .border(0.dp, Color.Transparent, shape = RoundedCornerShape(10.dp)),
    ){

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(55.dp)
                .background(backgroundColor)
                .border(2.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
                .clickable { onClick(index) },
            contentAlignment = Alignment.Center
        ){
            Text(
                color = textColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                text = "${colorName.uppercase()}",
            )
        }

    }
}

@Composable
fun SizeItems(availableSizeList: List<String>, selectedIndex: Int, changeTheSelectedSizeIndex: (Int) -> Unit){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        itemsIndexed(availableSizeList) { index, size ->
            AvailableSizeItem(
                index = index,
                selectedIndex = selectedIndex,
                size = size,
                onClick = {newIndex -> changeTheSelectedSizeIndex(newIndex)}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableSizeItem(index: Int, selectedIndex: Int, size: String, onClick: (Int) -> Unit){
    var padding = if(index == 0) 0.dp else 10.dp
    var border = if(index == selectedIndex) 2.5.dp else 0.dp

    Column(
        modifier = Modifier
            .padding(start = padding)
            .size(width = 60.dp, height = 25.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
    ){
      Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(width = 60.dp, height = 25.dp)
                .background(Color.White)
                .border(width = border, color = ButtonColor, shape = RoundedCornerShape(10.dp))
                .clickable { onClick(index) },
            contentAlignment = Alignment.Center
        ){
            Text(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                text = "${size}"
            )
        }
    }
}

@Composable
fun DetailsFooterSection(navControllerApp: NavHostController){
    Row(
       modifier = Modifier
           .fillMaxWidth(1f)
           .fillMaxHeight(1f)
           .background(VeryLightGray)
           .fillMaxHeight(0.4f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

    ){
       /* FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            enabled = false,
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Continuer")
        }*/
        Row(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, bottom = 10.dp)
            ){
            ButtonComponent(label = "Continuer", enable= true)
        }

    }
}

@Preview
@Composable
fun DetailsPreview(){
    var navControllerApp = rememberNavController()
    Column {
        DetailsTopBarSection(navControllerApp)
        DetailsBodySection()
        DetailsFooterSection(navControllerApp);
    }
}
