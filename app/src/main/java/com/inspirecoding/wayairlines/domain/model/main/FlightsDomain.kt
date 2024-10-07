package com.inspirecoding.wayairlines.domain.model.main

import android.os.Parcelable
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightsDomain(
    val flightsDataDomain: List<FlightsDataDomain>? = null
): Parcelable
