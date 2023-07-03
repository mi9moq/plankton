package com.example.plancton.presentation.state

import com.example.plancton.domain.entity.ErrorType

sealed interface LoginState {

    object Initial : LoginState

    object Loading : LoginState

    data class Error(val errorType: ErrorType) : LoginState
}