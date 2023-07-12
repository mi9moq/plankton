package com.example.plancton.data.repository

import com.example.plancton.data.datasource.EventRemoteDataSource
import com.example.plancton.domain.entity.EventRequest
import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.domain.repository.EventRepository
import java.util.Date
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource
) : EventRepository {

    override suspend fun createSingle(event: EventRequest) {
        eventRemoteDataSource.createSingle(event)
    }

    override suspend fun get(startDate: Date, endDate: Date): List<UserEvent> =
        eventRemoteDataSource.getList(startDate, endDate)

    override suspend fun delete(id: String) {
        eventRemoteDataSource.delete(id)
    }
}