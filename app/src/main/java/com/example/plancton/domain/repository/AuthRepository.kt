package com.example.plancton.domain.repository

import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.entity.RegistrationRequest

interface AuthRepository {

    suspend fun login(auth: Auth): String

    suspend fun register(registrationRequest: RegistrationRequest)
}