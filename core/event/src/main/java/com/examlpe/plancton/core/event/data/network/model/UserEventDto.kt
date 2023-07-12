package com.examlpe.plancton.core.event.data.network.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalTime

data class UserEventDto(
    val date: LocalDate,
    val time: LocalTime,
    val description: String,
    @SerializedName("event_group_id")
    val groupId: String,
    @SerializedName("event_id")
    var id: String,
)