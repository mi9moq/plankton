package com.example.plancton.data.datasource

import android.util.Log
import com.example.plancton.data.converter.AuthConverter
import com.example.plancton.domain.entity.Auth
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun login(auth: Auth): String

    suspend fun register(auth: Auth)
}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authConverter: AuthConverter,
) : AuthRemoteDataSource {

    override suspend fun login(auth: Auth): String =
        "STUB"

    override suspend fun register(auth: Auth) {
        Log.d("register", "STUB")
    }
}