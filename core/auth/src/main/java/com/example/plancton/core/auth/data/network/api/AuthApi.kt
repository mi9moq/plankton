package com.example.plancton.core.auth.data.network.api

import com.example.plancton.core.auth.data.network.model.AuthDto
import com.example.plancton.core.auth.data.network.model.AuthResponse
import com.example.plancton.core.auth.data.network.model.RegistrationRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/signup")
    suspend fun register(
        @Body user: RegistrationRequestDto
    ): AuthResponse

    @POST("/api/login")
    suspend fun login(
        @Body auth: AuthDto
    ): AuthResponse
}