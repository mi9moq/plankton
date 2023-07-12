package com.example.plancton.domain.usecase

import com.example.plancton.domain.entity.EventRequest
import com.example.plancton.domain.repository.EventRepository
import javax.inject.Inject

class CreateSingleEventUseCase @Inject constructor(
    private val repository: EventRepository
) {

    suspend operator fun invoke(event: EventRequest) = repository.createSingle(event)
}