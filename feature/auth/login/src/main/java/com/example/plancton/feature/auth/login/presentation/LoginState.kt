package com.example.plancton.feature.auth.login.presentation

import com.example.plancton.core.auth.domain.entity.AuthErrorType

sealed interface LoginState {

    object Initial : LoginState

    object Loading : LoginState

    data class Error(val authErrorType: AuthErrorType) : LoginState
}