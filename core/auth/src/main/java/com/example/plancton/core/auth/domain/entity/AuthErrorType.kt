package com.example.plancton.core.auth.domain.entity

enum class AuthErrorType {
    CONNECTION,
    USER_NOT_FOUND,
    UNKNOWN,
    WRONG_EMAIL,
    WRONG_PASSWORD;
}