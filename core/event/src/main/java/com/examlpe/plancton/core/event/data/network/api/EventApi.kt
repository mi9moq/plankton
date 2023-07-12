package com.examlpe.plancton.core.event.data.network.api

import com.examlpe.plancton.core.event.data.network.model.UserEventDto
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface EventApi {

    @POST("/api/event")
    suspend fun createSingle(
        @Body event: EventRequest,
    ): UserEventDto
}