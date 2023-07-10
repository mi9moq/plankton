package com.example.plancton.presentation.registration

import com.example.plancton.core.auth.domain.entity.AuthErrorType

sealed interface RegistrationState {

    object Initial : RegistrationState

    object Loading : RegistrationState

    data class Error(val authErrorType: AuthErrorType) : RegistrationState
}