package com.example.plancton.data.converter

import com.example.plancton.data.network.model.AuthModel
import com.example.plancton.domain.entity.Auth
import javax.inject.Inject

class AuthConverter @Inject constructor() {

    fun convert(from: Auth): AuthModel =
        AuthModel(
            fullName = from.fullName,
            password = from.password
        )
}