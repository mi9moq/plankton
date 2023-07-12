package com.example.plancton.core.auth.utils

import com.example.plancton.core.auth.data.network.model.AuthDto
import com.example.plancton.core.auth.data.network.model.AuthResponse
import com.example.plancton.core.auth.data.network.model.RegistrationRequestDto
import com.example.plancton.core.auth.domain.entity.Auth
import com.example.plancton.core.auth.domain.entity.RegistrationRequest

object Data {

    const val token = "TOKEN"

    val auth = Auth(email = "EMAIL", password = "PASSWORD")
    val authDto = AuthDto(email = "EMAIL", password = "PASSWORD")

    val registrationRequest = RegistrationRequest(
        email = "EMAIL",
        fullName = "FULL NAME",
        password = "PASSWORD"
    )
    val registrationRequestDto = RegistrationRequestDto(
        email = "EMAIL",
        fullName = "FULL NAME",
        password = "PASSWORD"
    )

    val authResponse = AuthResponse(
        token = token
    )
}