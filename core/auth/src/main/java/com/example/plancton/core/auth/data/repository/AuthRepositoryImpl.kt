package com.example.plancton.core.auth.data.repository

import com.example.plancton.core.auth.data.converter.AuthConverter
import com.example.plancton.core.auth.data.converter.RegistrationRequestConverter
import com.example.plancton.core.auth.data.datasource.AuthRemoteDataSource
import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.RegistrationRequest
import com.example.plancton.core.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    private val authConverter: AuthConverter,
    private val registrationRequestConverter: RegistrationRequestConverter,
) : AuthRepository {
    override suspend fun login(auth: Auth): String =
        remoteDataSource.login(auth.let(authConverter::convert))

    override suspend fun register(registrationRequest: RegistrationRequest): String =
        remoteDataSource.register(registrationRequest.let(registrationRequestConverter::convert))
}