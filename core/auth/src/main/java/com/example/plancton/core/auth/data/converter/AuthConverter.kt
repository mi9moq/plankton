package com.example.plancton.core.auth.data.converter

import com.example.plancton.core.auth.data.network.model.AuthDto
import com.example.plancton.core.auth.domain.entity.Auth
import javax.inject.Inject

class AuthConverter @Inject constructor() {

    fun convert(from: Auth): AuthDto =
        AuthDto(
            email = from.email,
            password = from.password
        )
}