package com.example.getmarketadmin.screens.home.components.imagePicker

import androidx.lifecycle.ViewModel
import com.example.getmarketadmin.screens.data.SelectedImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ImagePickerViewModel: ViewModel() {

    private val _selectedImages = MutableStateFlow<List<SelectedImage>>(emptyList())
    val selectedImages = _selectedImages.asStateFlow()

    fun addSelectedImage(image: SelectedImage) {
        val newList = _selectedImages.value.toMutableList().apply { add(image) }
        _selectedImages.value = newList
    }


    fun removeSelectedImage(image: SelectedImage) {
        val newList = _selectedImages.value.toMutableList().apply { remove(image) }
        _selectedImages.value = newList
    }
}