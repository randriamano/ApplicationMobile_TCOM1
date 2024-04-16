package com.example.e_comget.screens.Home.Components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.e_comget.DataStoreViewModel
import com.example.e_comget.Datoum.model.item.ProductCommandedDetails
import com.example.e_comget.Datoum.model.item.ProductDetail
import com.example.e_comget.GlobalViewModel
import com.example.e_comget.MainViewModel
import com.example.e_comget.Routes.MyAccountScreens
import com.example.e_comget.ui.theme.PoloColorBlack
import com.example.e_comget.ui.theme.PoloColorGray
import com.example.e_comget.ui.theme.PoloColorNavyBlue
import com.example.e_comget.ui.theme.PoloColorRed
import com.example.e_comget.ui.theme.PoloColorWhite
import com.example.e_comget.ui.theme.Red
import com.example.e_comget.ui.theme.VeryLightGray
import kotlinx.coroutines.delay


//TODO
//Adding counting product to command (UI)
//Create an object to store all data selected by user to send to the next screen (ProductOrderScreen)
//  If not signined : redirect into sign in screen before productOrder
//Logic to enable the "Continue" button when all data are complete

sealed class UpdateAction {
    data class UpdateColorChosen(val newColorChosen: String) : UpdateAction()
    data class UpdateSizeChosen(val newSizeChosen: String) : UpdateAction()
    data class UpdateAll(val newProductCommandedDetails: ProductCommandedDetails) : UpdateAction()
}

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProductDetailsScreen(
    navControllerApp: NavHostController,
    onGetProductDetails: () -> Unit,
    mainViewModel: MainViewModel
) {
    var isLoading by remember {
        mutableStateOf(true)
    }

    var productToCommand by remember {
        mutableStateOf(
            ProductCommandedDetails(
                productId = 0,
                productCategory = "",
                productPrice = "",
                productName = "",
                productColorChosen = "",
                productDescription = "",
                productIsPaid = false,
                productSizeChosen = ""
            )
        )
    }

    LaunchedEffect(Unit) {
        delay(500)
        isLoading = false
    }

    DisposableEffect(Unit) {
        mainViewModel.reinitializeTheProductFetchedById()
        onGetProductDetails()

        onDispose {}
    }

    val product = mainViewModel.uiStateProductFetchedById.value.data
    val uiState = mainViewModel.uiStateProductFetchedById.value

    if (isLoading) {
        onGetProductDetails()
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (product == null) onGetProductDetails()
        else {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(35.dp),
                )
            } else if (!uiState.error.isNullOrEmpty()) {
                Text(text = "Error")
                Button(onClick = onGetProductDetails) {
                    Text(text = "Recharger")
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    DetailsTopBarSection(
                        navControllerApp,
                        product = product,
                        mainViewModel = mainViewModel
                    )
                    DetailsBodySection(
                        product = product,
                        productToCommand = productToCommand
                    ) { action ->
                        when (action) {
                            is UpdateAction.UpdateSizeChosen -> {
                                productToCommand =
                                    productToCommand.copy(productSizeChosen = action.newSizeChosen)
                            }

                            is UpdateAction.UpdateColorChosen -> {
                                productToCommand =
                                    productToCommand.copy(productColorChosen = action.newColorChosen)
                            }

                            is UpdateAction.UpdateAll -> {
                                productToCommand = action.newProductCommandedDetails
                            }
                        }
                    }
                    DetailsFooterSection(navControllerApp, product = product);
                }
            }
        }
    }
}

@Composable
fun DetailsTopBarSection(
    navControllerApp: NavHostController,
    product: ProductDetail,
    mainViewModel: MainViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.White)
    ) {
        ImageCarousel(images = product.productImageURLList)
        DetailHeader(navControllerApp = navControllerApp, mainViewModel = mainViewModel)
    }
}

@Composable
fun DetailHeader(navControllerApp: NavHostController, mainViewModel: MainViewModel) {
    IconButton(
        onClick = {
            mainViewModel.reinitializeTheProductFetchedById()
            navControllerApp.popBackStack()
        },
        enabled = true,
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "back",
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun ImageCarousel(images: List<String>) {
    var currentIndex by remember { mutableStateOf(0) }

    val Images: ArrayList<AsyncImagePainter> = ArrayList<AsyncImagePainter>()
    val globalViewModel: GlobalViewModel = viewModel()
    val apiURL = globalViewModel.apiUrl.toString()

    for (imagePath in images) {
        Images.add(rememberAsyncImagePainter(apiURL + imagePath))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.50f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                painter = Images[currentIndex],
                contentDescription = null,
//               contentScale = ContentScale.FillBounds,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .align(Alignment.Center),
                Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex >= 0
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(25.dp)),
                        contentAlignment = Alignment.Center
                    ) {
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
                    ) {
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
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 60.dp, height = 25.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        text = "${currentIndex + 1} / ${images.size}"
                    )
                }
            }

        }
    }

}

@Composable
fun DetailsBodySection(
    product: ProductDetail,
    productToCommand: ProductCommandedDetails,
    onUpdateAction: (UpdateAction) -> Unit
) {
    val scrollState = rememberScrollState();
    var selectedColorIndex by remember {
        mutableStateOf(0)
    }

    var selectedSizeIndex by remember {
        mutableStateOf(0)
    }

    fun changeSelectedColorIndex(newVal: Int) {
        selectedColorIndex = newVal;
    }

    fun changeSelectedSizeIndex(newSize: Int) {
        selectedSizeIndex = newSize;
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(VeryLightGray)
            .fillMaxHeight(0.85f)
            .padding(10.dp)
    ) {
        Column(

        ) {
            Text(
                text = "Ar ${product.productPrice}",
                fontSize = 17.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Red
            )
            Text(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                text = "${product.productName}"
            )
        }
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth(1f),
            Arrangement.SpaceBetween
        ) {
            Text(
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                text = product.productDescription
            )
            Spacer(modifier = Modifier.height(15.dp))
            if (product.availableColorList!!.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    Arrangement.SpaceBetween
                ) {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        text = "Color : ${selectedColorIndex + 1}. ${product.availableColorList[selectedColorIndex]}",
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    ColorItems(
                        product.availableColorList, selectedColorIndex,
                        changeTheSelectedColorIndex = ::changeSelectedColorIndex
                    )
                }
            }
            if (product.availableSizeList!!.size > 0) {
                Spacer(modifier = Modifier.height(20.dp))
                Column {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        text = "Size : ${product.availableSizeList[selectedSizeIndex]}",
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    SizeItems(
                        availableSizeList = product.availableSizeList,
                        selectedIndex = selectedSizeIndex,
                        changeTheSelectedSizeIndex = ::changeSelectedSizeIndex
                    )
                }
            }
        }
    }
}

@Composable
fun ColorItems(
    availableColorList: List<String>,
    selectedIndex: Int,
    changeTheSelectedColorIndex: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        itemsIndexed(availableColorList) { index, colorName ->
            AvailableColorItem(
                index = index,
                indexSelected = selectedIndex,
                colorName = colorName,
                onClick = { newIndex -> changeTheSelectedColorIndex(newIndex) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableColorItem(index: Int, indexSelected: Int, colorName: String, onClick: (Int) -> Unit) {
    val mapColor: MutableMap<String, Color> = hashMapOf(
        "Black" to PoloColorBlack,
        "Navy Blue" to PoloColorNavyBlue,
        "Red" to PoloColorRed,
        "Gray" to PoloColorGray,
        "White" to PoloColorWhite
    )

    val textColor: Color = if (colorName.equals("White")) Color.Black else Color.White;
    var borderColor: Color = Color.Transparent;
    val nullableColor = mapColor[colorName];
    val backgroundColor = nullableColor ?: Color.Black;

    if (index == indexSelected) {
        borderColor = if (colorName.equals("White")) Color.Black else Color.White;
    }

    val padding = if (index == 0) 0.dp else 10.dp

    Column(
        modifier = Modifier
            .padding(start = padding)
            .size(50.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
            .border(0.dp, Color.Transparent, shape = RoundedCornerShape(10.dp)),
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color = backgroundColor, shape = RoundedCornerShape(10.dp))
                .border(2.dp, color = borderColor, shape = RoundedCornerShape(10.dp))
                .clickable(enabled = true, onClick = { onClick(index) }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                color = textColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                text = colorName.uppercase(),
            )
        }
    }
}

@Composable
fun SizeItems(
    availableSizeList: List<String>,
    selectedIndex: Int,
    changeTheSelectedSizeIndex: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        itemsIndexed(availableSizeList) { index, size ->
            AvailableSizeItem(
                index = index,
                selectedIndex = selectedIndex,
                size = size,
                onClick = { newIndex -> changeTheSelectedSizeIndex(newIndex) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableSizeItem(index: Int, selectedIndex: Int, size: String, onClick: (Int) -> Unit) {
    val padding = if (index == 0) 0.dp else 10.dp
    val border = if (index == selectedIndex) 3.dp else 0.dp

    Column(
        modifier = Modifier
            .padding(start = padding)
            .size(width = 60.dp, height = 25.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
    ) {
        Box(
            modifier = Modifier
                .size(width = 60.dp, height = 25.dp)
                .border(width = border, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .clickable(enabled = true, onClick = { onClick(index) }),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                text = size
            )
        }
    }
}

@Composable
fun DetailsFooterSection(navControllerApp: NavHostController, product: ProductDetail?) {
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()

    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(VeryLightGray),
//            .fillMaxHeight(0.4f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        ) {

        if (!dataStoreViewModel.isLoggedIn.collectAsState(initial = true).value) {
            FilledTonalButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                enabled = true,
                onClick = {
                    navControllerApp.navigate(MyAccountScreens.Login.route)
                },
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Text(text = "Se connecter")
            }
        } else {
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth(0.75f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                enabled = true,
                onClick = {
                }
            ) {
                Text(text = "Commander")
            }
        }
    }
}

@Preview
@Composable
fun DetailsPreview() {
    var navControllerApp = rememberNavController()
    Column {
////        CountingUI()
//        DetailsTopBarSection(navControllerApp)
//        DetailsBodySection()
//        DetailsFooterSection(navControllerApp);
    }
}
