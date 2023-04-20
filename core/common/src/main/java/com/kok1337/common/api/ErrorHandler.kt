package com.kok1337.common.api

interface ErrorHandler {
    fun handleError(exception: Exception)
    fun getUserMessage(exception: Throwable): String
}