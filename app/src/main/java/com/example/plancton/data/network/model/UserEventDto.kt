package com.example.plancton.data.network.model

import java.sql.Time
import java.util.Date
import java.util.UUID

data class UserEventDto(
    val date: Date,
    val time: Time,
    val description: String,
    var eventGroupId: String? = null,
    var id: UUID? = null
)