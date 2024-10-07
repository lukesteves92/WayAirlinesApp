package com.inspirecoding.wayairlines.data.util.exception

import com.inspirecoding.wayairlines.util.Constants.Text.EMPTY_STRING_DEFAULT

sealed class WayAirlinesException(
    override val message: String? = EMPTY_STRING_DEFAULT,
    val code: String? = EMPTY_STRING_DEFAULT
) : Throwable() {
    data object ErrorNetworkException : WayAirlinesException()
    class DefaultError(message: String, code: String) : WayAirlinesException(message, code)
}