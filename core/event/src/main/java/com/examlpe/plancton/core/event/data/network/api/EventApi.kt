package com.examlpe.plancton.core.event.data.network.api

import com.examlpe.plancton.core.event.data.network.model.UserEventDto
import com.examlpe.plancton.core.event.domain.entity.EventRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

interface EventApi {

    @POST("/api/event")
    suspend fun createSingle(
        @Body event: EventRequest,
    ): UserEventDto

    @GET("/api/events")
    suspend fun getAll(
        @Query("start_date") startDate: LocalDate, @Query("end_date") endDate: LocalDate,
    ): List<UserEventDto>
}