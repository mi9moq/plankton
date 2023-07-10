package com.example.plancton.core.auth.domain.usecase

import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(auth: Auth): String =
        repository.login(auth)
}