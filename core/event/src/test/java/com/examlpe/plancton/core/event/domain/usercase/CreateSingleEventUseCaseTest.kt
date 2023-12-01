package com.examlpe.plancton.core.event.domain.usercase

import com.examlpe.plancton.core.event.domain.repository.EventRepository
import com.examlpe.plancton.core.event.domain.usecase.CreateSingleEventUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import utils.EventData

class CreateSingleEventUseCaseTest {

    private val repository: EventRepository = mock()
    private val useCase = CreateSingleEventUseCase(repository)
    private val event = EventData.request

    @Test
    fun `create EXPECT create event`() = runTest {

        useCase(event)

        verify(repository).createSingle(event)
    }
}