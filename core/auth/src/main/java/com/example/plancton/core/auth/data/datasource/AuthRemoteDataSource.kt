package com.example.plancton.core.auth.data.datasource

import com.example.plancton.core.auth.data.converter.AuthConverter
import com.example.plancton.core.auth.data.converter.RegistrationRequestConverter
import com.example.plancton.core.auth.data.network.api.AuthApi
import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.RegistrationRequest
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun login(auth: Auth): String

    suspend fun register(registrationRequest: RegistrationRequest): String
}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authConverter: AuthConverter,
    private val registrationRequestConverter: RegistrationRequestConverter,
    private val authApi: AuthApi,
) : AuthRemoteDataSource {

    override suspend fun login(auth: Auth): String {
        val response = authApi.login(auth.let(authConverter::convert))
        return response.token
    }

    override suspend fun register(registrationRequest: RegistrationRequest): String {
        val response =
            authApi.register(registrationRequest.let(registrationRequestConverter::convert))
        return response.token
    }
}