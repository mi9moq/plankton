package com.examlpe.plancton.core.event.domain.usecase

import com.examlpe.plancton.core.event.domain.repository.EventRepository
import javax.inject.Inject

class DeleteEventUseCase @Inject constructor(
    private val repository: EventRepository
) {

    suspend operator fun invoke(id: String) = repository.delete(id)
}