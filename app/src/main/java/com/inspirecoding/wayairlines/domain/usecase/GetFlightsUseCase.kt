package com.inspirecoding.wayairlines.domain.usecase

import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.domain.repository.FlightsRepository
import com.inspirecoding.wayairlines.extensions.string.toBrazilianDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFlightsUseCase(
    private val repository: FlightsRepository
) {
    fun getFlights(): Flow<List<FlightsDataDomain>?> {
        return repository.getFlights()
            .map { data ->
                data?.map { flights ->
                    flights.copy(
                        startDate = flights.startDate?.toBrazilianDate(),
                        endDate = flights.endDate?.toBrazilianDate()
                    )
                }
            }
    }
}