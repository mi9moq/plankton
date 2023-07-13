package com.examlpe.plancton.core.event.data.datasource

import com.examlpe.plancton.core.event.data.network.api.EventApi
import com.examlpe.plancton.core.event.data.network.model.UserEventDto
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import java.time.LocalDate
import javax.inject.Inject

interface EventRemoteDataSource {

    suspend fun createSingle(event: EventRequest)

    suspend fun getAll(startDate: LocalDate, endDate: LocalDate): List<UserEventDto>

    suspend fun delete(id: String)
}

class EventRemoteDataSourceImpl @Inject constructor(
    private val eventApi: EventApi,
) : EventRemoteDataSource {

    override suspend fun createSingle(event: EventRequest) {
        eventApi.createSingle(event)
    }

    override suspend fun getAll(startDate: LocalDate, endDate: LocalDate): List<UserEventDto> =
        eventApi.getAll(startDate, endDate)

    override suspend fun delete(id: String) {
    }
}