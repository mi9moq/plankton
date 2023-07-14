package com.example.plancton.core.auth.data.converter

import com.example.plancton.core.auth.data.network.model.RegistrationRequestDto
import com.example.plancton.core.auth.domain.entity.RegistrationRequest
import javax.inject.Inject

class RegistrationRequestConverter @Inject constructor() {

    fun convert(from: RegistrationRequest): RegistrationRequestDto = RegistrationRequestDto(
        email = from.email,
        fullName = from.fullName,
        password = from.password,
    )
}