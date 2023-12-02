package com.example.plancton.core.token.domain.usecase

import com.example.plancton.core.token.domain.repository.TokenRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DeleteTokenUseCaseTest {

    private val repository: TokenRepository = mock()
    private val useCase = DeleteTokenUseCase(repository)

    @Test
    fun `delete EXPECT delete token`() {

        useCase()

        verify(repository).delete()
    }
}