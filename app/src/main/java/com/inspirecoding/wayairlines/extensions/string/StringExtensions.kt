package com.inspirecoding.wayairlines.extensions.string

import com.google.gson.Gson
import com.inspirecoding.wayairlines.domain.model.GenericException
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun String.decode(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())

internal fun String?.toDefaultError() = try {
    Gson().fromJson(
        this,
        GenericException::class.java
    )
} catch (e: Exception) {
    GenericException(code = "999", message = "generic error")
}

internal fun String?.containsTagHtml() =
    (this != null && this.contains("html"))