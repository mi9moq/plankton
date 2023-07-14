package com.examlpe.plancton.core.event.domain.usecase

import com.examlpe.plancton.core.event.domain.entity.EventRequest
import com.examlpe.plancton.core.event.domain.repository.EventRepository
import javax.inject.Inject

class CreateSingleEventUseCase @Inject constructor(
    private val repository: EventRepository
) {

    suspend operator fun invoke(event: EventRequest) = repository.createSingle(event)
}