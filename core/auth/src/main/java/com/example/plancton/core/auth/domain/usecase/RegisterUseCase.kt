package com.example.plancton.core.auth.domain.usecase

import com.example.plancton.core.auth.domain.entity.RegistrationRequest
import com.example.plancton.core.auth.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(registrationRequest: RegistrationRequest): String =
        repository.register(registrationRequest)
}