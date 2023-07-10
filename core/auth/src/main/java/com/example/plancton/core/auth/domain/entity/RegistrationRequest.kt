package com.example.plancton.core.auth.domain.entity

data class RegistrationRequest(
    val email: String,
    val fullName: String,
    val password: String,
)
