package com.example.plancton.domain.usecase

import com.example.plancton.domain.entity.RegistrationRequest
import com.example.plancton.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(registrationRequest: RegistrationRequest) {
        repository.register(registrationRequest)
    }
}