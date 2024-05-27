package com.example.e_comget

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GlobalViewModel : ViewModel() {
    var apiUrl: String = "https://applicationmobile-tcom1.onrender.com"
//    var apiUrl: String = "http://192.168.43.226:3000"
}