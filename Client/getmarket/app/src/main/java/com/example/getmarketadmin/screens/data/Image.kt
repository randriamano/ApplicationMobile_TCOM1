package com.example.getmarketadmin.screens.data

import android.graphics.Bitmap
import android.net.Uri

data class SelectedImage(
    val url: Uri,
    val bitmap: Bitmap // JPG PNG WEBP
)