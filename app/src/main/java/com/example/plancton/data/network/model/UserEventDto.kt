package com.example.plancton.data.network.model

import com.example.plancton.domain.entity.ReplayType
import java.sql.Time
import java.util.Date
import java.util.UUID

data class UserEventDto(
    val date: Date,
    val time: Time,
    val description: String,
    var eventGroupId: String? = null,
    var id: UUID? = null,
    var replay: ReplayType?,
)