package com.example.plancton.core.user.domain.entity

enum class UserErrorType {
    UNAUTHORIZED, //http401
    CONNECTION,
    NOT_FOUND,//not found
    UNKNOWN;
}