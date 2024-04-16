package com.example.e_comget.Datoum.repository

import android.util.Log
import com.example.e_comget.Datoum.GetApi
import com.example.e_comget.Datoum.ResultGet
import com.example.e_comget.Datoum.model.item.UserItemToSend
import com.example.e_comget.Datoum.model.item.UserLoginItem
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepository @Inject constructor(
    private val api: GetApi
) {
    suspend fun getProducts() = flow {
        emit(ResultGet.Loading())
        val products = api.getProducts().body()?.product
        emit(ResultGet.Success(data = products))
    }.catch { error ->
        emit(ResultGet.Error(message = error.message!!))
    }

    suspend fun getProductsByCategoryName(category: String) = flow {
        emit(ResultGet.Loading())
        val products = api.getProductsByCategoryName(category).body()?.product
        emit(ResultGet.Success(data = products))
    }.catch { error ->
        emit(ResultGet.Error(message = error.message!!))
    }

    suspend fun getProductById(id: Int) = flow {
        emit(ResultGet.Loading())
        val product = api.getProductById(id).body()
        emit(ResultGet.Success(data = product))
    }.catch { error ->
        emit(ResultGet.Error(message = error.message!!))
    }

    suspend fun getNom() = flow {
        emit(ResultGet.Loading())
        val name = api.getNom().body()
        emit(ResultGet.Success(data = name))
    }.catch { error ->
        emit(ResultGet.Error(message = error.message!!))
    }

    suspend fun getCommands(category: String) = flow {
        emit(ResultGet.Loading())
        val commands = api.getCommandedProducts(category).body()?.products
        emit(ResultGet.Success(data = commands))
    }.catch { error ->
        emit(ResultGet.Error(message = error.message!!))
    }

    suspend fun register(userItemToSend: UserItemToSend) = flow {
        emit(ResultGet.Loading())
        val authenticationWrapper = api.register(userItemToSend).body()
        emit(ResultGet.Success(data = authenticationWrapper))
    }.catch { error ->
        emit(ResultGet.Error(message = error.message!!))
    }

    suspend fun login(userLoginItem: UserLoginItem) = flow {
        emit(ResultGet.Loading())
        val loggedUser = api.login(userLoginItem).body()
        emit(ResultGet.Success(data = loggedUser))
    }.catch { error ->
        emit(ResultGet.Error(message = error.message!!))
    }
}