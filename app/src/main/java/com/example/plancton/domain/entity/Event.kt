package com.example.plancton.domain.entity

import java.sql.Time
import java.util.Date

data class Event(
    val date: Date,
    val time: Time,
    val description: String,
    val eventGroupId: String,
    val section: Section,
    val user: User
)
