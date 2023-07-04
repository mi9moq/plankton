package com.example.plancton.data.datasource

import android.util.Log
import com.example.plancton.data.converter.AuthConverter
import com.example.plancton.data.converter.UserRegistrationConverter
import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.entity.UserRegistration
import javax.inject.Inject

interface AuthRemoteDataSource {

    suspend fun login(auth: Auth): String

    suspend fun register(userRegistration: UserRegistration)
}

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authConverter: AuthConverter,
    private val userRegistrationConverter: UserRegistrationConverter,
) : AuthRemoteDataSource {

    override suspend fun login(auth: Auth): String =
        "STUB"

    override suspend fun register(userRegistration: UserRegistration) {
        Log.d("register", "STUB")
    }
}