package com.example.kingpowertest.data.model

import java.lang.Exception

sealed class ResultWrapper<out T : Any?> {
    data class Success<out T : Any?>(val value: T) : ResultWrapper<T>()
    data class Error(val message: String, val cause: Exception? = null) : ResultWrapper<Nothing>()
}