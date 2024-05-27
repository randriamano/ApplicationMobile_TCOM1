package com.example.e_comget.screens.Home.Components

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.draw.clip
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
import com.example.e_comget.CommandProductViewModel
import com.example.e_comget.DataStoreViewModel
import com.example.e_comget.Datoum.model.item.ColorItem
import com.example.e_comget.Datoum.model.item.CommandItemToSend
import com.example.e_comget.Datoum.model.item.ProductCommandedDetails
import com.example.e_comget.Datoum.model.item.ProductDetail
import com.example.e_comget.GlobalViewModel
import com.example.e_comget.MainViewModel
import com.example.e_comget.Routes.MyAccountScreens
import com.example.e_comget.ui.theme.ButtonColor
import com.example.e_comget.ui.theme.Red
import com.example.e_comget.ui.theme.VeryLightGray
import kotlinx.coroutines.delay


//TODO
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
            Log.d("", "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh")
            Log.d("", "${product}")
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
                    modifier = Modifier.background(VeryLightGray),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    DetailsTopBarSection(
                        navControllerApp,
                        product = product,
                        mainViewModel = mainViewModel
                    )
                    DetailsBodySection(
                        product = product
                    )
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
            navControllerApp.navigateUp()
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
            .fillMaxHeight(0.45f)
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
                    .padding(start = 8.dp, end = 8.dp)
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
    commandProductViewModel: CommandProductViewModel = viewModel(),
) {
    val scrollState = rememberScrollState();
    var selectedColorIndex by remember {
        mutableStateOf(0)
    }

    var selectedSizeIndex by remember {
        mutableStateOf(0)
    }

    commandProductViewModel.productColorChosen =
        product.availableColorList?.get(selectedColorIndex) ?: ColorItem("", "")

    commandProductViewModel.productSizeChosen = product.availableSizeList?.get(selectedSizeIndex)
        ?: ""

    fun changeSelectedColorIndex(newVal: Int) {
        selectedColorIndex = newVal;
        commandProductViewModel.productColorChosen =
            product.availableColorList?.get(selectedColorIndex) ?: ColorItem("", "")
    }

    fun changeSelectedSizeIndex(newSize: Int) {
        selectedSizeIndex = newSize;
        commandProductViewModel.productSizeChosen = product.availableSizeList?.get(selectedSizeIndex)
            ?: ""
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(VeryLightGray)
            .fillMaxHeight(0.85f)
            .padding(10.dp)
    ) {
        Column{
            Text(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                text = product.productName
            )

            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                text = product.productDescription
            )
            Spacer(Modifier.height(8.dp))

            Text(
                text = "${product.productPrice} Ar",
                fontSize = 35.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                color = Red
            )
        }
        Spacer(Modifier.height(7.dp))
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth(1f),
            Arrangement.SpaceBetween
        ) {

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
                        text = "Couleur : ${product.availableColorList[selectedColorIndex].colorItemName}",
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    ColorItems(
                        product.availableColorList,
                        selectedColorIndex,
                        changeTheSelectedColorIndex = ::changeSelectedColorIndex
                    )
                }
            }
            if (product.availableSizeList!!.isNotEmpty()) {
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
        }
    }
}

@Composable
fun ColorItems(
    availableColorList: List<ColorItem>,
    selectedIndex: Int,
    changeTheSelectedColorIndex: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(1f)
    ) {
        itemsIndexed(availableColorList) { index, _ ->
            AvailableColorItem(
                availableColorList = availableColorList,
                index = index,
                indexSelected = selectedIndex,
                onClick = { newIndex -> changeTheSelectedColorIndex(newIndex) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableColorItem(availableColorList: List<ColorItem>, index: Int, indexSelected: Int, onClick: (Int) -> Unit) {
    val colorItem = availableColorList[index]
    val colorName = colorItem.colorItemName
    val colorCode = Color(android.graphics.Color.parseColor(colorItem.colorItemCode))

    val textColor: Color = if (colorName == "White") Color.Black else Color.White;
    var borderColor: Color = Color.Transparent;


    if (index == indexSelected) {
        borderColor = if (colorName == "White") Color.Black else Color.White;
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
                .clip(RoundedCornerShape(12.dp))
                .size(55.dp)
                .background(colorCode)
                .border(2.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
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
    val border = if (index == selectedIndex) 2.5.dp else 0.dp

    Column(
        modifier = Modifier
            .padding(start = padding)
            .size(width = 60.dp, height = 25.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(width = 60.dp, height = 25.dp)
                .border(width = border, color = ButtonColor, shape = RoundedCornerShape(10.dp))
                .background(Color.White)
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
fun DetailsFooterSection(
    navControllerApp: NavHostController,
    product: ProductDetail?,
    commandProductViewModel: CommandProductViewModel = viewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()
    var show by remember { mutableStateOf(false) }



    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .padding(start = 25.dp, end = 25.dp)
            .background(VeryLightGray),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,

        ) {

        if (!dataStoreViewModel.isLoggedIn.collectAsState(initial = true).value) {

            ButtonComponent("Se connecter", true, onClick = {
                navControllerApp.navigate(MyAccountScreens.Login.route)
            } )

        } else {

            ButtonComponent("Commander", true, onClick = {
                show = show.not()
                Log.d("", "Value of show = ${show}")
            })

            if (show) {
                AlertDialog(
                    onDismissRequest = { show = false },
                    title = { Text("Confirmer votre commande") },
                    text = { Text("${product?.productName}, ${commandProductViewModel.productSizeChosen}, ${commandProductViewModel.productColorChosen.colorItemName}") },
                    confirmButton = {
                        TextButton(onClick = {
                            Log.d("", "heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeere")
//                            Log.d("", "${dataStoreViewModel.userId.}")
                            Log.d("", "ttttttttttttttttttttttttthhhhhhhhhhhhhhhhhhereeeeeeeeeeeee")

                            if (product != null) {
                                mainViewModel.commandProduct(CommandItemToSend(
                                    productId = product.productId,
                                    studentId = 1,
                                    productColorChosen = commandProductViewModel.productColorChosen,
                                    productSizeChosen = commandProductViewModel.productSizeChosen,
                                    productIsPayed = false
                                ))
                                show = false
                            }
                        }) {
                            Text("Confirmer".uppercase())
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { show = false }) {
                            Text("Annuler".uppercase())
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun ButtonComponent(label : String, enable: Boolean, onClick: () -> Unit){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
        shape = MaterialTheme.shapes.extraLarge,
        enabled = enable
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
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
