package com.example.plancton.core.auth.data.datasource

import com.example.plancton.core.auth.data.network.api.AuthApi
import com.example.plancton.core.auth.data.network.model.AuthDto
import com.example.plancton.core.auth.data.network.model.RegistrationRequestDto
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun login(authDto: AuthDto): String

    suspend fun register(registrationRequestDto: RegistrationRequestDto): String
}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi,
) : AuthRemoteDataSource {

    override suspend fun login(authDto: AuthDto): String {
        val response = authApi.login(authDto)
        return response.token
    }

    override suspend fun register(registrationRequestDto: RegistrationRequestDto): String {
        val response =
            authApi.register(registrationRequestDto)
        return response.token
    }
}