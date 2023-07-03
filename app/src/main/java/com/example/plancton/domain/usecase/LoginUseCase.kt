package com.example.plancton.domain.usecase

import com.example.plancton.domain.entity.Auth
import com.example.plancton.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(auth: Auth): String =
        repository.login(auth)
}