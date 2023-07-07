package com.example.plancton.domain.usecase

import com.example.plancton.domain.repository.TokenRepository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(
    private val repository: TokenRepository
) {

    operator fun invoke(token: String) {
        repository.set(token)
    }
}