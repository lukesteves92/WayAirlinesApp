package com.inspirecoding.wayairlines.data.remote.services.responseapi

import com.inspirecoding.wayairlines.data.util.exception.WayAirlinesException

sealed class ResponseApi<out T> {
    class Success<T>(var data: T?) : ResponseApi<T>()
    class ErrorException(var errorException: WayAirlinesException) : ResponseApi<Nothing>()
}