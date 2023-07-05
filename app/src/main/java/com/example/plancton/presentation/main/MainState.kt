package com.example.plancton.presentation.main

import com.example.plancton.domain.entity.UserEvent

sealed interface MainState{

    object Initial: MainState

    object Loading: MainState

    data class Content(val content: List<UserEvent>): MainState

    data class Error(val message: String): MainState
}