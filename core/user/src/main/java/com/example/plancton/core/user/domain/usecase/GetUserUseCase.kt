package com.example.plancton.core.user.domain.usecase

import com.example.plancton.core.user.domain.entity.User
import com.example.plancton.core.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {

    suspend operator fun invoke(id: String): User =
        repository.get(id)
}