package com.examlpe.plancton.core.event.domain.usercase

import com.examlpe.plancton.core.event.domain.repository.EventRepository
import com.examlpe.plancton.core.event.domain.usecase.DeleteEventUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DeleteEventUseCaseTest {

    private val repository: EventRepository = mock()
    private val useCase = DeleteEventUseCase(repository)
    private val id = "1"

    @Test
    fun `delete EXPECT delete event`() = runTest {

        useCase(id)

        verify(repository).delete(id)
    }
}