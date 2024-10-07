package com.inspirecoding.wayairlines.domain.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightsDataDomain(
    val airplaneName: String? = null,
    val arrivalAirport: String? = null,
    val arrivalTime: String? = null,
    val completionStatus: String? = null,
    val departureAirport: String? = null,
    val departureTime: String? = null,
    val endDate: String? = null,
    val flightId: String? = null,
    val startDate: String? = null,
    val status: String? = null
) : Parcelable
