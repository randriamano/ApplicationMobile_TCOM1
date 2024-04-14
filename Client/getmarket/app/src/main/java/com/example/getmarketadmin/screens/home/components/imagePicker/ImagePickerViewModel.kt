package com.example.getmarketadmin.screens.home.components.imagePicker

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ImagePickerViewModel: ViewModel() {
    private val _selectedImages = MutableStateFlow<List<String>>(emptyList())
    val selectedImages = _selectedImages.asStateFlow()

    fun addSelectedImage(path: String) {
        val newList = _selectedImages.value.toMutableList().apply { add(path) }
        _selectedImages.value = newList
    }

    fun clearSelectedImages() {
        _selectedImages.value = emptyList()
    }

    fun removeSelectedImage(path: String) {
        val newList = _selectedImages.value.toMutableList().apply { remove(path) }
        _selectedImages.value = newList
    }
}