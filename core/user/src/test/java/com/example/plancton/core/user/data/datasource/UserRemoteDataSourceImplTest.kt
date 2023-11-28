package com.example.plancton.core.user.data.datasource

import com.example.plancton.core.user.data.api.UserApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import utils.UserData

class UserRemoteDataSourceImplTest {

    private val userApi: UserApi = mock()
    private val dataSource = UserRemoteDataSourceImpl(userApi)

    private val id = "0"
    private val changeUserRequest = UserData.changeUserRequest
    private val user = UserData.user

    @Test
    fun `change EXPECT changing`() = runTest {
        dataSource.change(id, changeUserRequest)

        verify(userApi).change(id, changeUserRequest)
    }

    @Test
    fun `get EXPECT User`() = runTest {
        whenever(userApi.get(id)) doReturn user

        val expected = user
        val actual = dataSource.get(id)

        assertEquals(expected, actual)
    }
}