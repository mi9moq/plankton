package com.example.plancton.core.auth.data.network.model

data class RegistrationRequestDto(
    val email: String,
    val fullName: String,
    val password: String,
)
