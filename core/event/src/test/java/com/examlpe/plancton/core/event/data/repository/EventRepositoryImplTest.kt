package com.examlpe.plancton.core.event.data.repository

import com.examlpe.plancton.core.event.data.converter.EventConverter
import com.examlpe.plancton.core.event.data.datasource.EventRemoteDataSource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import utils.EventData

class EventRepositoryImplTest {

    private val eventRemoteDataSource: EventRemoteDataSource = mock()
    private val converter: EventConverter = mock()

    private val repository = EventRepositoryImpl(eventRemoteDataSource, converter)

    private val request = EventData.request
    private val eventList = EventData.eventList
    private val eventListDto = EventData.listDto
    private val startDate = EventData.startDate
    private val endDate = EventData.endDate
    private val event = EventData.event
    private val eventDto = EventData.eventDto
    private val id = "123"

    @Test
    fun `create single EXPECT create event`() = runTest {
        repository.createSingle(request)

        verify(eventRemoteDataSource).createSingle(request)
    }

    @Test
    fun `delete EXPECT delete event`() = runTest {
        repository.delete(id)

        verify(eventRemoteDataSource).delete(id)
    }

    @Test
    fun `get all EXPECT events list`() = runTest {
        whenever(eventRemoteDataSource.getAll(startDate, endDate)) doReturn eventListDto
        whenever(converter.revert(eventDto)) doReturn event

        val expect = eventList
        val actual = repository.getAll(startDate, endDate)

        assertEquals(expect,actual)
    }
}