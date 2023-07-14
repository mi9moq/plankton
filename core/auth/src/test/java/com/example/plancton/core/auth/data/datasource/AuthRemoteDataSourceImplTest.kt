package com.example.plancton.core.auth.data.datasource

import com.example.plancton.core.auth.data.network.api.AuthApi
import com.example.plancton.core.auth.utils.Data
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AuthRemoteDataSourceImplTest {

    private val authApi: AuthApi = mock()

    private val dataSource = AuthRemoteDataSourceImpl(
        authApi
    )

    private val authDto = Data.authDto
    private val authResponse = Data.authResponse

    private val registrationRequestDto = Data.registrationRequestDto

    private val token = Data.token

    @Test
    fun `login EXPECT token`() = runTest {
        whenever(authApi.login(authDto)) doReturn authResponse

        val expected = token
        val actual = dataSource.login(authDto)

        assertEquals(expected, actual)
    }

    @Test
    fun `register EXPECT token`() = runTest {
        whenever(authApi.register(registrationRequestDto)) doReturn authResponse

        val expected = token
        val actual = dataSource.register(registrationRequestDto)

        assertEquals(expected, actual)
    }
}