package com.example.plancton.data.converter

import com.example.plancton.data.network.model.RegistrationRequestModel
import com.example.plancton.domain.entity.RegistrationRequest
import javax.inject.Inject

class RegistrationRequestConverter @Inject constructor() {

    fun convert(from: RegistrationRequest): RegistrationRequestModel = RegistrationRequestModel(
        email = from.email,
        fullName = from.fullName,
        password = from.password,
    )
}