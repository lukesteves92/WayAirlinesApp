package com.inspirecoding.wayairlines.data.remote.mapper

import com.inspirecoding.wayairlines.data.remote.model.data.FlightsDataResponse
import com.inspirecoding.wayairlines.data.remote.model.main.FlightsResponse
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.domain.model.main.FlightsDomain

internal fun FlightsResponse.toDomain() = FlightsDomain(
    flightsDataDomain = this.flightsDataResponses.map { it.toDomain() }
)

internal fun FlightsDataResponse.toDomain() = FlightsDataDomain(
    airplaneName = this.airplaneName,
    arrivalAirport = this.arrivalAirport,
    arrivalTime = this.arrivalTime,
    completionStatus = this.completionStatus,
    departureAirport = this.departureAirport,
    departureTime = this.departureTime,
    endDate = this.endDate,
    flightId = this.flightId,
    startDate = this.startDate,
    status = this.status
)