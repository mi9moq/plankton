package com.example.plancton.domain.repository

import com.example.plancton.domain.entity.UserEvent
import java.util.Date

interface EventRepository {

    suspend fun create(userEvent: UserEvent)

    suspend fun getEvents(startDate: Date, endDate: Date): List<UserEvent>
}