package com.inspirecoding.wayairlines.data.util.singleorthrow

import com.inspirecoding.wayairlines.data.util.exception.WayAirlinesException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single

suspend inline fun <T> Flow<T>.singleOrThrow(
    success: ((T) -> Unit),
    error: ((WayAirlinesException) -> Unit)
) {
    try {
        success.invoke(single())
    } catch (e: WayAirlinesException) {
        error.invoke(e)
    }
}