package com.example.plancton.data.datasource

import com.example.plancton.data.converter.EventConverter
import com.example.plancton.data.network.model.UserEventDto
import com.example.plancton.domain.entity.UserEvent
import java.sql.Time
import java.util.Date
import javax.inject.Inject

interface EventRemoteDataSource {

    suspend fun create(userEvent: UserEvent)

    suspend fun getEvents(startDate: Date, endDate: Date): List<UserEvent>

    suspend fun deleteEvent(event: UserEvent)
}

class EventRemoteDataSourceImpl @Inject constructor(
    private val eventConverter: EventConverter
) : EventRemoteDataSource {

    override suspend fun create(userEvent: UserEvent) {
        listEvent.add(userEvent.let(eventConverter::convert))
    }

    override suspend fun getEvents(startDate: Date, endDate: Date): List<UserEvent> {
        return listEvent.map(eventConverter::revert)
    }

    override suspend fun deleteEvent(event: UserEvent) {
        listEvent.remove(event.let(eventConverter::convert))
    }

    private companion object {
        val listEvent = mutableListOf(
            UserEventDto(
                date = Date(1688947200000),
                time = Time(3650000),
                description = "Дождаться api",
                eventGroupId = null,
                id = null,
                replay = null
            ),
            UserEventDto(
                date = Date(1689465600000),
                time = Time(3650000),
                description = "Выпить пива",
                eventGroupId = null,
                id = null,
                replay = null
            )
        )
    }
}