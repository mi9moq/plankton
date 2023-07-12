package com.example.plancton.data.network.api

import com.example.plancton.data.network.model.UserEventDto
import com.example.plancton.domain.entity.EventRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface EventApi {

    @POST("/api/event")
    suspend fun createSingle(
        @Body event: EventRequest
    ): UserEventDto
}