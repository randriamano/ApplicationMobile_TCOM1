package com.example.getmarketadmin.screens.home.components.imagePicker

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import androidx.compose.runtime.*
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor


@Composable
fun MyScreen(viewModel: ImagePickerViewModel) {
    // Autres composants et logique de l'écran

    pickImage(viewModel)
}


@OptIn(ExperimentalCoilApi::class, ExperimentalFoundationApi::class)
@Composable
fun pickImage(viewModel: ImagePickerViewModel){

    //var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    //val selectImages by viewModel.selectedImages.collectAsState(emptyList())
    val selectImages by viewModel.selectedImages.collectAsState()

    /*val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            selectImages = it
        }*/
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            //viewModel.clearSelectedImages()
            it.forEach { uri ->
                viewModel.addSelectedImage(uri.toString())
            }
        }

    Row(
        verticalAlignment = Alignment.CenterVertically,
      //  Modifier.fillMaxSize(),
       // horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LargeFloatingActionButton(
            onClick = {  galleryLauncher.launch("image/*") },
            containerColor = BgButtonColor,
            contentColor = ButtonColor,
            modifier = Modifier.padding(end = 15.dp , bottom = 15.dp)
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.", Modifier.size(30.dp))
        }


        //LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        LazyRow() {
            items(selectImages) { uri ->
                Image(
                    painter = rememberImagePainter(uri),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .size(100.dp)
                        .clickable {
                            viewModel.removeSelectedImage(uri)
                        }
                )
            }
        }

    }



}