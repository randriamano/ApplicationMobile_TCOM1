package com.example.e_comget

import androidx.lifecycle.ViewModel
import com.example.e_comget.Datoum.model.item.ColorItem

class CommandProductViewModel : ViewModel() {
    var productId = -1
    var studentId = -1
    var productSizeChosen = ""
    var productColorChosen = ColorItem(colorItemName = "", colorItemCode = "")
}