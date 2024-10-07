package com.inspirecoding.wayairlines.data.remote.model.data

import com.google.gson.annotations.SerializedName

data class FlightsDataResponse(
    @SerializedName("airplane_name")
    val airplaneName: String? = null,
    @SerializedName("arrival_airport")
    val arrivalAirport: String? = null,
    @SerializedName("arrival_time")
    val arrivalTime: String? = null,
    @SerializedName("completion_status")
    val completionStatus: String? = null,
    @SerializedName("departure_airport")
    val departureAirport: String? = null,
    @SerializedName("departure_time")
    val departureTime: String? = null,
    @SerializedName("end_date")
    val endDate: String? = null,
    @SerializedName("flight_id")
    val flightId: String? = null,
    @SerializedName("start_date")
    val startDate: String? = null,
    @SerializedName("status")
    val status: String? = null
)