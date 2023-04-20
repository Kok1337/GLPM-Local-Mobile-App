package com.kok1337.common.api

sealed class ExecutionResult

object ExecutionSuccess : ExecutionResult()

object ExecutionPending : ExecutionResult()

class ExecutionError(
    val exception: Exception
)