package com.example.plancton.data.datasource

import android.util.Log
import com.example.plancton.data.converter.EventConverter
import com.example.plancton.domain.entity.UserEvent
import javax.inject.Inject

interface EventRemoteDataSource {

    suspend fun create(userEvent: UserEvent)
}

class EventRemoteDataSourceImpl @Inject constructor(
    private val eventConverter: EventConverter
) : EventRemoteDataSource {

    override suspend fun create(userEvent: UserEvent) {
        //TODO api.create(event.let{eventConverter::convert})
        val dto = eventConverter.convert(userEvent)
        Log.d("EventRemoteDataSourceImpl",dto.toString())
    }
}