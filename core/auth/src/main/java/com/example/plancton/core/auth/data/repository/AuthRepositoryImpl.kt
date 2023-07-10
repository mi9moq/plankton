package com.example.plancton.core.auth.data.repository

import com.example.plancton.core.auth.data.datasource.AuthRemoteDataSource
import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.RegistrationRequest
import com.example.plancton.core.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun login(auth: Auth): String =
        remoteDataSource.login(auth)

    override suspend fun register(registrationRequest: RegistrationRequest) {
        remoteDataSource.register(registrationRequest)
    }
}