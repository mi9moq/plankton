package com.example.plancton.core.token.domain.usecase

import com.example.plancton.core.token.domain.repository.TokenRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetTokenUseCaseTest {

    private val repository: TokenRepository = mock()
    private val useCase = GetTokenUseCase(repository)
    private val token = "token"

    @Test
    fun `get EXPECT token`() {

        whenever(repository.get()) doReturn token

        val expected = token
        val actual = useCase()

        assertEquals(expected, actual)
    }
}