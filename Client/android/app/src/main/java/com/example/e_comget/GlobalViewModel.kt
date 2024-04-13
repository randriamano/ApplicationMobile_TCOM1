package com.example.e_comget

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GlobalViewModel : ViewModel() {
//    var apiUrl : String = ("http://192.168.43.10:3000")
    var apiUrl: String = ("http://192.168.88.220:3000")
    var isSignedIn: MutableState<Boolean> = mutableStateOf(true)
    var myId: String = ""

    fun changeIsSignedIn(){
        isSignedIn.value = !isSignedIn.value
    }
}