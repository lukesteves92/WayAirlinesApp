package com.inspirecoding.wayairlines.data.remote.services.wrapper

import com.inspirecoding.wayairlines.data.remote.services.responseapi.ResponseApi
import retrofit2.Response

interface RequestWrapper {
    suspend fun <T : Any> wrapper(call: suspend () -> Response<T>): ResponseApi<T>
}