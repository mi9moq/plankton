package com.example.plancton.core.user.domain.usecase

import com.example.plancton.core.user.domain.repository.UserRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import utils.UserData

class GetUserUseCaseTest {

    private val repository: UserRepository = mock()
    private val useCase = GetUserUseCase(repository)

    private val id = "0"
    private val user = UserData.user

    @Test
    fun `invoke EXPECT User`() = runTest {
        whenever(repository.get(id)) doReturn user

        val expected = user
        val actual = useCase(id)

        assertEquals(expected, actual)
    }
}