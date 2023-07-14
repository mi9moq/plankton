package com.examlpe.plancton.core.event.domain.entity

enum class EventErrorType {
    UNAUTHORIZED, //http401
    CONNECTION,
    NOT_FOUND,//not found
    UNKNOWN;
}