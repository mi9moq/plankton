package com.examlpe.plancton.core.event.domain.usecase

import com.examlpe.plancton.core.event.domain.entity.UserEvent
import com.examlpe.plancton.core.event.domain.repository.EventRepository
import java.time.LocalDate
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EventRepository,
) {
    suspend operator fun invoke(startDate: LocalDate, endDate: LocalDate): List<UserEvent> =
        repository.getAll(startDate, endDate)
}