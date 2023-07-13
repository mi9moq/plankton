package com.examlpe.plancton.core.event.domain.repository

import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.entity.UserEvent
import java.time.LocalDate

interface EventRepository {

    suspend fun createSingle(event: EventRequest)

    suspend fun getAll(startDate: LocalDate, endDate: LocalDate): List<UserEvent>

    suspend fun delete(id: String)
}