package com.example.plancton.domain.repository

import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.entity.UserRegistration

interface AuthRepository {

    suspend fun login(auth: Auth): String

    suspend fun register(userRegistration: UserRegistration)
}