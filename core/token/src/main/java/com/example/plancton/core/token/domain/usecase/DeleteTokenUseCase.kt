package com.example.plancton.core.token.domain.usecase

import com.example.plancton.core.token.domain.repository.TokenRepository
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(
    private val repository: TokenRepository,
) {

    operator fun invoke() {
        repository.delete()
    }
}