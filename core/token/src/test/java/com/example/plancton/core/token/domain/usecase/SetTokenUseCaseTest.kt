package com.example.plancton.core.token.domain.usecase

import com.example.plancton.core.token.domain.repository.TokenRepository
import org.mockito.kotlin.mock
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify

class SetTokenUseCaseTest {

    private val repository: TokenRepository = mock()
    private val useCase = SetTokenUseCase(repository)
    private val token = "1"

    @Test
    fun `set EXPECT set token`() {

        useCase(token)

        verify(repository).set(token)
    }
}