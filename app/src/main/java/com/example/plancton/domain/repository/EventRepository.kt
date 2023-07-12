package com.example.plancton.domain.repository

import com.example.plancton.domain.entity.EventRequest
import com.example.plancton.domain.entity.UserEvent
import java.util.Date

interface EventRepository {

    suspend fun createSingle(event: EventRequest)

    suspend fun get(startDate: Date, endDate: Date): List<UserEvent>

    suspend fun delete(id: String)
}