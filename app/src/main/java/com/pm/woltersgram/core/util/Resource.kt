package com.pm.woltersgram.core.util

sealed class Resource<T>(val data: T? = null, val errorCode: Int? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(errorCode: Int?) : Resource<T>(errorCode = errorCode)
    class Loading<T> : Resource<T>()
}