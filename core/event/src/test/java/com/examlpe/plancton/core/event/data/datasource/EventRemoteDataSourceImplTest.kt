package com.examlpe.plancton.core.event.data.datasource

import com.examlpe.plancton.core.event.data.converter.EventConverter
import com.examlpe.plancton.core.event.data.datasource.EventRemoteDataSource
import com.examlpe.plancton.core.event.data.network.api.EventApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import utils.EventData

class EventRemoteDataSourceImplTest {

    private val api: EventApi = mock()
    private val dataSource = EventRemoteDataSourceImpl(api)

    private val start = EventData.startDate
    private val end = EventData.endDate
    private val eventRequest = EventData.request
    private val eventsList = EventData.listDto

    @Test
    fun `create EXPECT new event`() = runTest {

        dataSource.createSingle(eventRequest)

        verify(api).createSingle(eventRequest)
    }

    @Test
    fun `get all EXPECT events list`() = runTest {
        whenever(api.getAll(start, end)) doReturn eventsList

        val expected = eventsList
        val actual = dataSource.getAll(start, end)

        assertEquals(expected, actual)
    }
}