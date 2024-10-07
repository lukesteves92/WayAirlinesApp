package com.inspirecoding.wayairlines.domain.usecase

import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.domain.repository.FlightsRepository
import kotlinx.coroutines.flow.Flow

class GetFlightsUseCase(
    private val repository: FlightsRepository
) {
    fun getFlights(): Flow<List<FlightsDataDomain>?> = repository.getFlights()
}