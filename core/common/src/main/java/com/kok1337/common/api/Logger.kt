package com.kok1337.common.api

interface Logger {
    fun log(message: String)
    fun err(exception: Throwable, message: String? = null)
}