package com.inspirecoding.wayairlines.data.remote.model.main

import com.google.gson.annotations.SerializedName
import com.inspirecoding.wayairlines.data.remote.model.data.FlightsDataResponse

data class FlightsResponse(
    @SerializedName("flights")
    val flightsDataResponses: List<FlightsDataResponse>? = null
)