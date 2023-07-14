package com.example.plancton.core.user.domain.repository

import com.example.plancton.core.user.domain.entity.ChangeUserRequest

interface UserRepository {

    suspend fun change(id: String, changeUserRequest: ChangeUserRequest)

    suspend fun get(id: String)
}