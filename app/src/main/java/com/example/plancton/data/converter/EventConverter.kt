package com.example.plancton.data.converter

import com.example.plancton.data.network.model.UserEventDto
import com.example.plancton.domain.entity.UserEvent
import javax.inject.Inject

class EventConverter @Inject constructor() {

    fun convert(from: UserEvent): UserEventDto = UserEventDto(
        date = from.date,
        time = from.time,
        description = from.description,
        groupId = from.groupId,
        id = from.id,
    )

    fun revert(from: UserEventDto): UserEvent = UserEvent(
        date = from.date,
        time = from.time,
        description = from.description,
        groupId = from.groupId,
        id = from.id,
    )
}