package com.example.plancton.domain.entity

data class RegistrationRequest(
    val email: String,
    val fullName: String,
    val password: String,
)
