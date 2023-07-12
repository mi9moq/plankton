package com.examlpe.plancton.core.event.data.repository

import com.examlpe.plancton.core.event.data.datasource.EventRemoteDataSource
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import com.examlpe.plancton.core.event.domain.repository.EventRepository
import java.util.Date
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
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