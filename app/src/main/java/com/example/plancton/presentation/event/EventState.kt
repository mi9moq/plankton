package com.example.plancton.presentation.event

import com.example.plancton.domain.entity.UserEvent
import java.sql.Time
import java.util.Date

sealed interface EventState {

    object Initial : EventState

    object Loading : EventState

    data class Content(val userEvent: UserEvent) : EventState

    data class Error(val message: String) : EventState
}