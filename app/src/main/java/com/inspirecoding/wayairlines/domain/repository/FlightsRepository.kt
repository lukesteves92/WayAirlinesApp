package com.inspirecoding.wayairlines.domain.repository

import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun getFlights(): Flow<List<FlightsDataDomain>?>
}