package com.example.plancton.core.user.domain.usecase

import com.example.plancton.core.user.domain.repository.UserRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import utils.UserData

class ChangeUserUseCaseTest {

    private val repository: UserRepository = mock()
    private val useCase = ChangeUserUseCase(repository)

    private val id = "0"
    private val changeUserRequest = UserData.changeUserRequest

    @Test
    fun `invoke EXPECT changing`() = runTest {
        useCase(id, changeUserRequest)

        verify(repository).change(id, changeUserRequest)
    }
}