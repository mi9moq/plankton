package com.example.plancton.domain.repository

import com.example.plancton.domain.entity.Auth

interface AuthRepository {

    suspend fun login(auth: Auth): String

    suspend fun register(auth: Auth)
}