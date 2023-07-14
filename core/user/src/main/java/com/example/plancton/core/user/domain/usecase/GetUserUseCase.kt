package com.example.plancton.core.user.domain.usecase

import com.example.plancton.core.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {

    suspend fun invoke(id: String) {
        repository.get(id)
    }
}