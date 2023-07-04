package com.example.plancton.domain.entity

import java.util.UUID

data class User (
    val fullName: String,
    val email: String,
    val id: UUID
)