package com.example.plancton.data.converter

import com.example.plancton.data.network.model.UserRegistrationModel
import com.example.plancton.domain.entity.UserRegistration

class UserRegistrationConverter {

    fun convert(from: UserRegistration): UserRegistrationModel = UserRegistrationModel(
        email = from.email,
        fullName = from.fullName,
        password = from.password,
    )
}