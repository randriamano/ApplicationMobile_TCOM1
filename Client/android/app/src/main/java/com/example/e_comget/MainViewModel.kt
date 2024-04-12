package com.example.e_comget

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_comget.Datoum.GetRepository
import com.example.e_comget.Datoum.ResultGet
import com.example.e_comget.Datoum.model.UIState
import com.example.e_comget.Datoum.model.UIStateName
import com.example.e_comget.Datoum.model.UIStateProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: GetRepository
): ViewModel() {
    var uiState = mutableStateOf(UIState())
    var uiStateName = mutableStateOf(UIStateName())
    var uiStateProductFetchedById = mutableStateOf(UIStateProduct())

    init{
        getProduct()
        getNom()
//        getProductById(2)
    }

    fun getProduct(){
        viewModelScope.launch {
            repository.getProducts().collect{ result ->
                when (result){
                    is ResultGet.Loading->{
                        uiState.value = UIState(
                            isLoading = true
                        )
                    }
                    is ResultGet.Success->{
                        uiState.value = UIState(isLoading = false, data = result.data!!)
                    }
                    is ResultGet.Error->{
                        uiState.value = UIState(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

//    fun getProductById(id : Int){
//        viewModelScope.launch {
//            repository.getProductById(id).collect{ result ->
//                when (result){
//                    is ResultGet.Loading->{
//                        uiStateProductFetchedById.value = UIStateProduct(
//                            isLoading = true
//                        )
//                    }
//                    is ResultGet.Success->{
//                        uiStateProductFetchedById.value = UIStateProduct(isLoading = false, data = result.data!!.body())
//                    }
//                    is ResultGet.Error->{
//                        uiStateProductFetchedById.value = UIStateProduct(isLoading = false, error = result.message)
//                    }
//                }
//            }
//        }
//    }

    fun getNom(){
        viewModelScope.launch {
            repository.getNom().collect{
                result ->
                when (result){
                    is ResultGet.Loading->{
                        uiStateName.value = UIStateName(
                            isLoading = true
                        )
                    }
                    is ResultGet.Success->{
                        uiStateName.value = UIStateName(isLoading = false, data = result.data!!)
                    }
                    is ResultGet.Error->{
                        uiStateName.value = UIStateName(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}















