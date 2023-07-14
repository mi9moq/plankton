package com.example.plancton.core.user.domain.usecase

import com.example.plancton.core.user.domain.entity.ChangeUserRequest
import com.example.plancton.core.user.domain.repository.UserRepository
import javax.inject.Inject

class ChangeUserUseCase @Inject constructor(
    private val repository: UserRepository,
) {

    suspend operator fun invoke(id: String, changeUserRequest: ChangeUserRequest) {
        repository.change(id, changeUserRequest)
    }
}