package com.examlpe.plancton.core.event.domain.repository

import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import java.util.Date

interface EventRepository {

    suspend fun createSingle(event: EventRequest)

    suspend fun get(startDate: Date, endDate: Date): List<UserEvent>

    suspend fun delete(id: String)
}