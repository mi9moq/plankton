package com.example.plancton.data.converter

import com.example.plancton.data.network.model.AuthDto
import com.example.plancton.domain.entity.Auth
import javax.inject.Inject

class AuthConverter @Inject constructor() {

    fun convert(from: Auth): AuthDto =
        AuthDto(
            email = from.email,
            password = from.password
        )
}