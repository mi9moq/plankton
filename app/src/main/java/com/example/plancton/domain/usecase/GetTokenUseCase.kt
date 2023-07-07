package com.example.plancton.domain.usecase

import com.example.plancton.domain.repository.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: TokenRepository,
) {

    operator fun invoke(): String =
        repository.get()
}