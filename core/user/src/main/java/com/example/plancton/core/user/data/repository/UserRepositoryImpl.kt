package com.example.plancton.core.user.data.repository

import com.example.plancton.core.user.data.datasource.UserRemoteDataSource
import com.example.plancton.core.user.domain.entity.ChangeUserRequest
import com.example.plancton.core.user.domain.entity.User
import com.example.plancton.core.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun change(id: String, changeUserRequest: ChangeUserRequest) {
        dataSource.change(id, changeUserRequest)
    }

    override suspend fun get(id: String): User =
        dataSource.get(id)
}