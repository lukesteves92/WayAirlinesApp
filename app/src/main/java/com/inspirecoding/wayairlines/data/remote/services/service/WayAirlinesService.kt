package com.inspirecoding.wayairlines.data.remote.services.service

import com.inspirecoding.wayairlines.data.remote.model.main.FlightsResponse
import retrofit2.Response
import retrofit2.http.GET

interface WayAirlinesService {
    @GET(KEY_ENDPOINT_DEFAULT_FLIGHTS)
    suspend fun getFlights(): Response<FlightsResponse>

    companion object {
        private const val KEY_ENDPOINT_DEFAULT_FLIGHTS = "/flights"
    }
}