package com.example.plancton.core.auth.data.repository

import com.example.plancton.core.auth.data.converter.AuthConverter
import com.example.plancton.core.auth.data.converter.RegistrationRequestConverter
import com.example.plancton.core.auth.data.datasource.AuthRemoteDataSource
import com.example.plancton.core.auth.utils.Data
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AuthRepositoryImplTest {

    private val dataSource: AuthRemoteDataSource = mock()
    private val authConverter: AuthConverter = mock()
    private val registrationRequestConverter: RegistrationRequestConverter = mock()

    private val repository = AuthRepositoryImpl(
        dataSource,
        authConverter,
        registrationRequestConverter
    )

    private val auth = Data.auth
    private val authDto = Data.authDto

    private val registrationRequest = Data.registrationRequest
    private val registrationRequestDto = Data.registrationRequestDto

    private val token = Data.token

    @Test
    fun `login EXPECT token`() = runTest {
        whenever(authConverter.convert(auth)) doReturn authDto
        whenever(dataSource.login(authDto)) doReturn token

        val expected = token
        val actual = repository.login(auth)

        assertEquals(expected, actual)
    }

    @Test
    fun `register EXPECT token`() = runTest {
        whenever(registrationRequestConverter.convert(registrationRequest)) doReturn registrationRequestDto
        whenever(dataSource.register(registrationRequestDto)) doReturn token

        val expected = token
        val actual = repository.register(registrationRequest)

        assertEquals(expected, actual)
    }
}