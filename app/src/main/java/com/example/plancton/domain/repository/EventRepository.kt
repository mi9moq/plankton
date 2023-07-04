package com.example.plancton.domain.repository

import com.example.plancton.domain.entity.UserEvent

interface EventRepository {

    suspend fun create(userEvent: UserEvent)
}