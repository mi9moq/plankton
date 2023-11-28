package com.example.plancton.core.user.data.repository

import com.example.plancton.core.user.data.datasource.UserRemoteDataSource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import utils.UserData

class UserRepositoryImplTest {

    private val dataSource: UserRemoteDataSource = mock()
    private val repository = UserRepositoryImpl(dataSource)

    private val id = "0"
    private val changeUserRequest = UserData.changeUserRequest
    private val user = UserData.user

    @Test
    fun `change EXPECT changing`() = runTest {
        repository.change(id, changeUserRequest)

        verify(dataSource).change(id, changeUserRequest)
    }

    @Test
    fun `get EXPECT User`() = runTest {
        whenever(dataSource.get(id)) doReturn user

        val expected = user
        val actual = repository.get(id)

        assertEquals(expected, actual)
    }
}