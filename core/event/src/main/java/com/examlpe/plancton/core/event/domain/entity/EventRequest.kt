package com.examlpe.plancton.core.event.domain.entity

import java.time.LocalDate
import java.time.LocalTime

data class EventRequest (
    val date: LocalDate,
    val time: LocalTime,
    val description: String?,
)