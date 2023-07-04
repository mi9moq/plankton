package com.example.plancton.domain.usecase

import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.domain.repository.EventRepository
import javax.inject.Inject

class CreateEventUseCase @Inject constructor(
    private val repository: EventRepository
) {

    suspend operator fun invoke(userEvent: UserEvent) = repository.create(userEvent)
}