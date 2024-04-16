package com.example.e_comget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comget.Datoum.dataStore.MYPreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val myPreferencesDataStore: MYPreferencesDataStore
) : ViewModel() {
    val isLoggedIn = myPreferencesDataStore.taskStatusFlow.map {
        it.isLoggedIn
    }
    val userCardNum = myPreferencesDataStore.taskStatusFlow.map {
        it.userCardNum
    }
    val userName = myPreferencesDataStore.taskStatusFlow.map {
        it.userName
    }
    val userFirstName = myPreferencesDataStore.taskStatusFlow.map {
        it.userFirstName
    }

    fun updateIsLoggedIn(isLoggedIn: Boolean) {
        viewModelScope.launch {
            myPreferencesDataStore.updateIsLoggedIn(isLoggedIn)
        }
    }

    fun updateUserCardNum(userCardNum: String) {
        viewModelScope.launch {
            myPreferencesDataStore.updateUserCardName(userCardNum)
        }
    }

    fun updtadeUserName(userName: String) {
        viewModelScope.launch {
            myPreferencesDataStore.updateUserName(userName)
        }
    }

    fun updateUserFirstName(userFirstName: String) {
        viewModelScope.launch {
            myPreferencesDataStore.updateUserFirstName(userFirstName = userFirstName)
        }
    }
}