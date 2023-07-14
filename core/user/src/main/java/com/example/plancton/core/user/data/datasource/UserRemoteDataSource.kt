package com.example.plancton.core.user.data.datasource

import com.example.plancton.core.user.data.api.UserApi
import com.example.plancton.core.user.domain.entity.ChangeUserRequest
import com.example.plancton.core.user.domain.entity.User
import javax.inject.Inject

interface UserRemoteDataSource {

    suspend fun change(id: String, changeUserRequest: ChangeUserRequest)

    suspend fun get(id: String): User
}

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: UserApi,
) : UserRemoteDataSource {

    override suspend fun change(id: String, changeUserRequest: ChangeUserRequest) {
        api.change(id, changeUserRequest)
    }

    override suspend fun get(id: String): User =
        api.get(id)
}