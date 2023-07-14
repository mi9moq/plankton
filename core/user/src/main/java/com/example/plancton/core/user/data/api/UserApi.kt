package com.example.plancton.core.user.data.api

import com.example.plancton.core.user.domain.entity.ChangeUserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UserApi {

    @PATCH("/api/user/{id}")
    suspend fun change(
        @Path("id") id: String,
        @Body changeUserRequest: ChangeUserRequest,
    )

    @GET("/api/user/{id}")
    suspend fun get(@Path("id") id: String)
}