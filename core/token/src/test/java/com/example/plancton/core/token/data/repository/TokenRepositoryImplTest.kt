package com.example.plancton.core.token.data.repository

import com.example.plancton.core.token.data.datasource.TokenLocalDataSource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TokenRepositoryImplTest {

    private val dataSource: TokenLocalDataSource = mock()
    private val repository = TokenRepositoryImpl(dataSource)
    private val token = "1"

    @Test
    fun `delete EXPECT delete token`() {

        repository.delete()

        verify(dataSource).delete()
    }
}