package com.example.plancton.data.datasource

import android.util.Log
import com.example.plancton.data.converter.AuthConverter
import com.example.plancton.data.converter.RegistrationRequestConverter
import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.entity.RegistrationRequest
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun login(auth: Auth): String

    suspend fun register(registrationRequest: RegistrationRequest)
}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authConverter: AuthConverter,
    private val registrationRequestConverter: RegistrationRequestConverter,
) : AuthRemoteDataSource {

    override suspend fun login(auth: Auth): String =
        "STUB"

    override suspend fun register(registrationRequest: RegistrationRequest) {
        Log.d("register", "STUB")
    }
}