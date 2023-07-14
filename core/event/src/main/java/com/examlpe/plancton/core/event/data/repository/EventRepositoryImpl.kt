package com.examlpe.plancton.core.event.data.repository

import com.examlpe.plancton.core.event.data.converter.EventConverter
import com.examlpe.plancton.core.event.data.datasource.EventRemoteDataSource
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import com.examlpe.plancton.core.event.domain.repository.EventRepository
import java.time.LocalDate
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val converter: EventConverter,
) : EventRepository {

    override suspend fun createSingle(event: EventRequest) {
        eventRemoteDataSource.createSingle(event)
    }

    override suspend fun getAll(startDate: LocalDate, endDate: LocalDate): List<UserEvent> =
        eventRemoteDataSource.getAll(startDate, endDate).map(converter::revert)

    override suspend fun delete(id: String) {
        eventRemoteDataSource.delete(id)
    }
}