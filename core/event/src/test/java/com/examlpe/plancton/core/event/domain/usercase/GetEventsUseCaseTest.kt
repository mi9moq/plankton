package com.examlpe.plancton.core.event.domain.usercase

import com.examlpe.plancton.core.event.domain.repository.EventRepository
import com.examlpe.plancton.core.event.domain.usecase.GetEventsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import utils.EventData

class GetEventsUseCaseTest {

    private val repository: EventRepository = mock()
    private val useCase = GetEventsUseCase(repository)
    private val start = EventData.startDate
    private val end = EventData.endDate
    private val events = EventData.eventList

    @Test
    fun `get EXPECT events list`() = runTest {

        whenever(repository.getAll(start, end)) doReturn events

        val expect = events
        val actual = useCase(start, end)

        assertEquals(expect, actual)
    }
}