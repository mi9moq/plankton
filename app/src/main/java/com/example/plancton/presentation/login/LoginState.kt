package com.example.plancton.presentation.login

import com.example.plancton.domain.entity.AuthErrorType

sealed interface LoginState {

    object Initial : LoginState

    object Loading : LoginState

    data class Error(val authErrorType: AuthErrorType) : LoginState
}