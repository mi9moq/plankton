package com.example.plancton.presentation.user

import com.example.plancton.core.user.domain.entity.User
import com.example.plancton.core.user.domain.entity.UserErrorType

sealed interface UserState {

    object Initial : UserState

    object Loading : UserState

    data class Content(val user: User) : UserState

    data class Error(val type: UserErrorType) : UserState
}