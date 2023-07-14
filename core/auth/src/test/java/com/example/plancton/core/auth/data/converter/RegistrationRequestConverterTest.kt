package com.example.plancton.core.auth.data.converter

import com.example.plancton.core.auth.utils.Data
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RegistrationRequestConverterTest {

    private val converter = RegistrationRequestConverter()

    private val registrationRequest = Data.registrationRequest
    private val registrationRequestDto = Data.registrationRequestDto

    @Test
    fun `convert EXPECT registration request dto`() {
        val expected = registrationRequestDto
        val actual = converter.convert(registrationRequest)

        Assertions.assertEquals(expected, actual)
    }
}