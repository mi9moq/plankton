package com.example.plancton.domain.usecase

import com.example.plancton.domain.entity.UserEvent
import com.example.plancton.domain.repository.EventRepository
import java.util.Date
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EventRepository
) {
    suspend operator fun invoke(startDate: Date, endDate: Date): List<UserEvent> =
        repository.get(startDate, endDate)
}