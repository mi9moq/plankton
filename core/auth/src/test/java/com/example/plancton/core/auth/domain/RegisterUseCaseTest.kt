package com.example.plancton.core.auth.domain

import com.example.plancton.core.auth.domain.repository.AuthRepository
import com.example.plancton.core.auth.domain.usecase.RegisterUseCase
import com.example.plancton.core.auth.utils.Data
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RegisterUseCaseTest {

    private val repository: AuthRepository = mock()
    private val useCase = RegisterUseCase(repository)

    private val registrationRequest = Data.registrationRequest

    private val token = Data.token

    @Test
    fun `invoke EXPECT token`() = runTest {
        whenever(repository.register(registrationRequest)) doReturn token

        val expected = token
        val actual = useCase(registrationRequest)

        Assertions.assertEquals(expected, actual)
    }
}