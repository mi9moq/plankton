package com.example.plancton.domain.entity

import java.sql.Time
import java.util.Date
import java.util.UUID

data class UserEvent(
    val date: Date,
    val time: Time,
    val description: String,
    var eventGroupId: String? = null,
    var id: UUID? = null,
    var replay: ReplayType? = null,
)
