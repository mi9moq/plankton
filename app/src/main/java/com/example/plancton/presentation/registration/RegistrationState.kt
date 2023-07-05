package com.example.plancton.presentation.registration

import com.example.plancton.domain.entity.ErrorType

sealed interface RegistrationState {

    object Initial : RegistrationState

    object Loading : RegistrationState

    data class Error(val errorType: ErrorType) : RegistrationState
}