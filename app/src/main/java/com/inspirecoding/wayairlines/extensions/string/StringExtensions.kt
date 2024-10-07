package com.inspirecoding.wayairlines.extensions.string

import com.google.gson.Gson
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.domain.model.exception.GenericException
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Locale

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

internal fun List<FlightsDataDomain>.groupFlightsByCategory(): Map<String, List<FlightsDataDomain>> {
    return mapOf(
        "todos os voos" to this,
        "voos concluídos" to this.filter { it.status == "CONCLUIDO" },
        "voos cancelados" to this.filter { it.status == "CANCELADO" },
        "voos a realizar" to this.filter { it.status == "CONCLUIDO" && it.completionStatus == "NO_HORARIO" },
        "voos atrasados" to this.filter { it.status == "CONCLUIDO" && it.completionStatus == "ATRASOU" }
    )
}

internal fun String.toBrazilianDate(): String {
    return try {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val date = inputDateFormat.parse(this)
        date?.let { outputDateFormat.format(it) } ?: this
    } catch (e: Exception) {
        this
    }
}

internal fun FlightsDataDomain.formatFlightDetails(): String {
    return "$endDate | Saída: $departureTime | Chegada: $arrivalTime"
}

