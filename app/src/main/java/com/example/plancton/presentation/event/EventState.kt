package com.example.plancton.presentation.event

import com.examlpe.plancton.core.event.domain.entity.UserEvent

sealed interface EventState {

    object Initial : EventState

    object Loading : EventState

    data class Content(val userEvent: UserEvent) : EventState

    data class Error(val message: String) : EventState
}