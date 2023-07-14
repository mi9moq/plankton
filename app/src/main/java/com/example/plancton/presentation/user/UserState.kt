package com.example.plancton.presentation.user

import com.example.plancton.core.user.domain.entity.User

sealed interface UserState {

    //TODO добавить остальные состояния
    object Initial: UserState

    data class Content(val user: User): UserState
}