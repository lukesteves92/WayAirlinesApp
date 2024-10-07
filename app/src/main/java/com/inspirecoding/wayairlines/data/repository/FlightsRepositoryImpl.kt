package com.inspirecoding.wayairlines.data.repository

import com.inspirecoding.wayairlines.data.remote.mapper.toDomain
import com.inspirecoding.wayairlines.data.remote.services.responseapi.ResponseApi
import com.inspirecoding.wayairlines.data.remote.services.service.WayAirlinesService
import com.inspirecoding.wayairlines.data.remote.services.wrapper.RequestWrapper
import com.inspirecoding.wayairlines.domain.model.data.FlightsDataDomain
import com.inspirecoding.wayairlines.domain.repository.FlightsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FlightsRepositoryImpl(
    private val wrapper: RequestWrapper,
    private val service: WayAirlinesService
): FlightsRepository {
    override fun getFlights(): Flow<List<FlightsDataDomain>?> = flow {
        val result = wrapper.wrapper {
            service.getFlights()
        }
        when (result) {
            is ResponseApi.Success -> emit(result.data?.flightsDataResponses?.map { it.toDomain() })

            is ResponseApi.ErrorException -> throw result.errorException
        }
    }.flowOn(Dispatchers.IO)
}