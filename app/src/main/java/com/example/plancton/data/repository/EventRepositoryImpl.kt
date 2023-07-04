package com.example.plancton.data.repository

import com.example.plancton.data.datasource.EventRemoteDataSource
import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.domain.repository.EventRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource
) : EventRepository {

    override suspend fun create(userEvent: UserEvent) {
        eventRemoteDataSource.create(userEvent)
    }
}