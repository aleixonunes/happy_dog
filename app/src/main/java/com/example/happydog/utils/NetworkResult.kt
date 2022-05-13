package com.example.happydog.utils

sealed class NetworkResult<T>(
    val message: T? = null,
    val status: String? = null
) {

    class Success<T>(message: T) : NetworkResult<T>(message)

    class Error<T>(status: String, message: T? = null) : NetworkResult<T>(message, status)

    class Loading<T> : NetworkResult<T>()

}