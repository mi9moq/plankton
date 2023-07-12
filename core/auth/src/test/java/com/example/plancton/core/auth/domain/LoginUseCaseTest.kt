package com.example.plancton.core.auth.domain

import com.example.plancton.core.auth.domain.repository.AuthRepository
import com.example.plancton.core.auth.domain.usecase.LoginUseCase
import com.example.plancton.core.auth.utils.Data
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoginUseCaseTest {

    private val repository: AuthRepository = mock()
    private val useCase = LoginUseCase(repository)

    private val auth = Data.auth

    private val token = Data.token

    @Test
    fun `invoke EXPECT token`() = runTest {
        whenever(repository.login(auth)) doReturn token

        val expected = token
        val actual = useCase(auth)

        assertEquals(expected, actual)
    }
}