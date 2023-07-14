package com.example.plancton.core.token.domain.usecase

import com.example.plancton.core.token.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: TokenRepository,
) {

    operator fun invoke(): String? =
        repository.get()
}