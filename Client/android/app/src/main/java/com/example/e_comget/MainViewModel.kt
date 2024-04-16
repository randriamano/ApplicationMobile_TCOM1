package com.example.e_comget

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comget.Datoum.ResultGet
import com.example.e_comget.Datoum.model.UIState
import com.example.e_comget.Datoum.model.UIStateAuthenticatedUser
import com.example.e_comget.Datoum.model.UIStateName
import com.example.e_comget.Datoum.model.UIStateProduct
import com.example.e_comget.Datoum.model.UIStateProductCommanded
import com.example.e_comget.Datoum.model.item.ProductDetail
import com.example.e_comget.Datoum.model.item.UserItemToSend
import com.example.e_comget.Datoum.model.item.UserLoginItem
import com.example.e_comget.Datoum.repository.GetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: GetRepository
) : ViewModel() {
    var uiState = mutableStateOf(UIState())
    var uiStateName = mutableStateOf(UIStateName())
    var uiStateProductFetchedById = mutableStateOf(UIStateProduct())
    var uiStateProductCommanded = mutableStateOf(UIStateProductCommanded())
    var productList: MutableState<List<ProductDetail>> = mutableStateOf(emptyList())
    var uiStateAuthenticatedUser = mutableStateOf(UIStateAuthenticatedUser())

    init {
        getProduct()
        getNom()
        getCommandedProducts("billets")
    }

    fun getProduct() {
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                when (result) {
                    is ResultGet.Loading -> {
                        uiState.value = UIState(
                            isLoading = true
                        )
                    }

                    is ResultGet.Success -> {
                        uiState.value = UIState(isLoading = false, data = result.data!!)
                        productList.value = result.data!!
                    }

                    is ResultGet.Error -> {
                        uiState.value = UIState(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun reinitializeProducts() {
        viewModelScope.launch {
            uiState.value = UIState()
            productList.value = emptyList()
        }
    }

    fun getProductsByCategoryName(category: String) {
        viewModelScope.launch {
            repository.getProductsByCategoryName(category).collect { result ->
                when (result) {
                    is ResultGet.Loading -> {
                        uiState.value = UIState(isLoading = true)
                    }

                    is ResultGet.Success -> {
                        uiState.value = UIState(isLoading = false, data = result.data!!)
                        productList.value = result.data
                    }

                    is ResultGet.Error -> {
                        uiState.value = UIState(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            repository.getProductById(id).collect { result ->
                when (result) {
                    is ResultGet.Loading -> {
                        uiStateProductFetchedById.value = UIStateProduct(
                            isLoading = true
                        )
                    }

                    is ResultGet.Success -> {
                        uiStateProductFetchedById.value =
                            UIStateProduct(isLoading = false, data = result.data!!)
                    }

                    is ResultGet.Error -> {
                        uiStateProductFetchedById.value =
                            UIStateProduct(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun reinitializeTheProductFetchedById() {
        viewModelScope.launch {
            uiStateProductFetchedById.value = UIStateProduct(isLoading = false, data = null)
        }
    }

    fun getNom() {
        viewModelScope.launch {
            repository.getNom().collect { result ->
                when (result) {
                    is ResultGet.Loading -> {
                        uiStateName.value = UIStateName(
                            isLoading = true
                        )
                    }

                    is ResultGet.Success -> {
                        uiStateName.value = UIStateName(isLoading = false, data = result.data!!)
                    }

                    is ResultGet.Error -> {
                        uiStateName.value = UIStateName(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun getCommandedProducts(category: String) {
        viewModelScope.launch {
            repository.getCommands(category).collect { result ->
                when (result) {
                    is ResultGet.Loading -> {
                        uiStateProductCommanded.value = UIStateProductCommanded(
                            isLoading = true
                        )
                    }

                    is ResultGet.Success -> {
                        uiStateProductCommanded.value =
                            UIStateProductCommanded(isLoading = false, data = result.data!!)
                    }

                    is ResultGet.Error -> {
                        uiStateProductCommanded.value =
                            UIStateProductCommanded(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun reinitializeTheCommandedProduct() {
        viewModelScope.launch {
            uiStateProductCommanded.value = UIStateProductCommanded(isLoading = false, data = null)
        }
    }

    fun register(userItem: UserItemToSend) {
        viewModelScope.launch {
            repository.register(userItem).collect { result ->
                when (result) {
                    is ResultGet.Loading -> {
                        uiStateAuthenticatedUser.value = UIStateAuthenticatedUser(isLoading = true)
                    }

                    is ResultGet.Success -> {
                        uiStateAuthenticatedUser.value =
                            UIStateAuthenticatedUser(isLoading = false, data = result.data)
                        Log.d("", "${uiStateAuthenticatedUser.value}")
                    }

                    is ResultGet.Error -> {
                        uiStateAuthenticatedUser.value =
                            UIStateAuthenticatedUser(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun login(userLoginItem: UserLoginItem) {
        viewModelScope.launch {
            repository.login(userLoginItem).collect { result ->
                when (result) {
                    is ResultGet.Loading -> {
                        uiStateAuthenticatedUser.value = UIStateAuthenticatedUser(isLoading = true)
                    }

                    is ResultGet.Success -> {
                        uiStateAuthenticatedUser.value =
                            UIStateAuthenticatedUser(isLoading = false, data = result.data)
                        Log.d("", "${uiStateAuthenticatedUser.value}")
                    }

                    is ResultGet.Error -> {
                        uiStateAuthenticatedUser.value =
                            UIStateAuthenticatedUser(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    fun reinitializeAuthenticatedUser() {
        uiStateAuthenticatedUser.value = UIStateAuthenticatedUser(isLoading = false, data = null)
    }
}















