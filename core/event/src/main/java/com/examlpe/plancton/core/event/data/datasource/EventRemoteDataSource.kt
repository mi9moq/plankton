package com.examlpe.plancton.core.event.data.datasource

import com.examlpe.plancton.core.event.data.network.api.EventApi
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import java.util.Date
import javax.inject.Inject

interface EventRemoteDataSource {

    suspend fun createSingle(event: EventRequest)

    suspend fun getList(startDate: Date, endDate: Date): List<UserEvent>

    suspend fun delete(id: String)
}

class EventRemoteDataSourceImpl @Inject constructor(
    private val eventApi: EventApi,
) : EventRemoteDataSource {

    override suspend fun createSingle(event: EventRequest) {
        eventApi.createSingle(event)
    }

    override suspend fun getList(startDate: Date, endDate: Date): List<UserEvent> {
        return emptyList()
    }

    override suspend fun delete(id: String) {
    }
}