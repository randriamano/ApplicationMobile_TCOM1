package com.example.e_comget.Datoum


sealed class ResultGet<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T> (data: T?): ResultGet<T>(data = data)
    class Error<T> (data: T? = null, message: String): ResultGet<T>(data = data, message = message)
    class Loading<T>(): ResultGet<T>()
}