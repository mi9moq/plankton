package com.example.plancton.core.user.domain.entity

data class ChangeUserRequest(
    val fullName: String,
    val email: String,
    val isActive: Boolean = true,
)
