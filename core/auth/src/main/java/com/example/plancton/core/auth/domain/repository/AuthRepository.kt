package com.example.plancton.core.auth.domain.repository

import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.RegistrationRequest

interface AuthRepository {

    suspend fun login(auth: Auth): String

    suspend fun register(registrationRequest: RegistrationRequest): String
}