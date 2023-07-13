package com.example.plancton.presentation.main

import com.examlpe.plancton.core.event.domain.entity.EventErrorType
import com.examlpe.plancton.core.event.domain.entity.UserEvent

sealed interface MainState{

    object Initial: MainState

    object Loading: MainState

    data class Content(val content: List<UserEvent>): MainState

    data class Error(val type: EventErrorType): MainState
}