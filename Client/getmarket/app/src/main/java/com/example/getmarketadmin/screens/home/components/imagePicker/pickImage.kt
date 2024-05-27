package com.example.getmarketadmin.screens.home.components.imagePicker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.getmarketadmin.screens.data.SelectedImage
import com.example.getmarketadmin.ui.theme.BgButtonColor
import com.example.getmarketadmin.ui.theme.ButtonColor
import java.io.FileNotFoundException


@Composable
fun PickImage(viewModel: ImagePickerViewModel, onSelected : (List<SelectedImage>) -> Unit, context: Context){

    val selectImages by viewModel.selectedImages.collectAsState()

   var selectedImagesList by remember { mutableStateOf(listOf<SelectedImage>()) }


    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            it.forEach { uri ->
                val bitmap = getBitmapFromUri(context, uri)

                if (bitmap != null) {
                    val selectedImage = SelectedImage(url = uri, bitmap = bitmap)
                    selectedImagesList = selectedImagesList + selectedImage
                    viewModel.addSelectedImage(selectedImage)
                } else {
                    Log.e("Bitmap Error", "Bitmap is null for URI: $uri")
                }
            }
        }

    Column{

        FloatingActionButton(
            onClick = {
                galleryLauncher.launch("image/*")
                      },
            containerColor = BgButtonColor,
            contentColor = ButtonColor,
            modifier = Modifier
                 .height(32.dp)
        ) {
            Row (
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                ){
                Text(
                    text = "Photos ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = ButtonColor,
                )
                Icon(Icons.Filled.Add, "Small floating action button.", Modifier.size(20.dp))

            }
        }

        LazyRow {
            items(selectedImagesList) { image ->

                Log.d("", "selected Image : $selectedImagesList")
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(end = 8.dp, top = 10.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(image.url),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .clickable {
                                selectedImagesList = selectedImagesList - image
                                viewModel.removeSelectedImage(image)
                            }
                    )
                }

            }
        }

        for (valeur in selectedImagesList) {
            verifyBitmap(valeur.url, valeur.bitmap, context)
        }

        onSelected(selectedImagesList)

    }

}

fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}

fun verifyBitmap(imageUri: Uri, bitmap: Bitmap, context: Context): Boolean {

    val originalBitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri))
    val originalWidth = originalBitmap.width
    val originalHeight = originalBitmap.height

    //Comparer les dimensions du bitmap avec celles de l'image d'origine
    val bitmapWidth = bitmap.width
    val bitmapHeight = bitmap.height
    if (bitmapWidth != originalWidth || bitmapHeight != originalHeight) {
        return false
    }

    //Comparer les données de pixels du bitmap avec celles de l'image d'origine
    val originalPixels = IntArray(originalWidth * originalHeight)
    originalBitmap.getPixels(originalPixels, 0, originalWidth, 0, 0, originalWidth, originalHeight)

    val bitmapPixels = IntArray(bitmapWidth * bitmapHeight)
    bitmap.getPixels(bitmapPixels, 0, bitmapWidth, 0, 0, bitmapWidth, bitmapHeight)

    return originalPixels.contentEquals(bitmapPixels)
}

