package com.example.plancton.data.repository

import com.example.plancton.data.datasource.AuthRemoteDataSource
import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
) : AuthRepository {
    override suspend fun login(auth: Auth): String =
        remoteDataSource.login(auth)

    override suspend fun register(auth: Auth) {
        remoteDataSource.register(auth)
    }
}