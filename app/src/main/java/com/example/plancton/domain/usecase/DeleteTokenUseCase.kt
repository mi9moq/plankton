package com.example.plancton.domain.usecase

import com.example.plancton.domain.repository.TokenRepository
import javax.inject.Inject

class DeleteTokenUseCase @Inject constructor(
    private val repository: TokenRepository,
) {

    operator fun invoke() {
        repository.delete()
    }
}