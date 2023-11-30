package com.examlpe.plancton.core.event.data.datasource

import com.examlpe.plancton.core.event.data.converter.EventConverter
import com.examlpe.plancton.core.event.data.datasource.EventRemoteDataSource
import com.examlpe.plancton.core.event.data.network.api.EventApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import utils.EventData

class EventRemoteDataSourceImplTest {

    private val api: EventApi = mock()
    private val dataSource = EventRemoteDataSourceImpl(api)

    private val eventRequest = EventData.request

    @Test
    fun `create EXPECT new event`() = runTest {

        dataSource.createSingle(eventRequest)

        verify(api).createSingle(eventRequest)
    }
}