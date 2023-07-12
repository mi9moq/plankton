package com.example.plancton.data.datasource

import com.example.plancton.data.network.api.EventApi
import com.example.plancton.domain.entity.EventRequest
import com.example.plancton.domain.entity.UserEvent
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