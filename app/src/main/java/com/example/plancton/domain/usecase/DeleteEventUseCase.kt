package com.example.plancton.domain.usecase

import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.domain.repository.EventRepository
import javax.inject.Inject

class DeleteEventUseCase @Inject constructor(
    private val repository: EventRepository
) {

    suspend operator fun invoke(event: UserEvent) = repository.deleteEvent(event)
}