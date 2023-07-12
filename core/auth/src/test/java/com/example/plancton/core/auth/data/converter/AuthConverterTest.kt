package com.example.plancton.core.auth.data.converter

import com.example.plancton.core.auth.utils.Data
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AuthConverterTest {

    private val converter = AuthConverter()

    private val auth = Data.auth
    private val authDto = Data.authDto

    @Test
    fun `convert EXPECT auth dto`() {
        val expected = authDto
        val actual = converter.convert(auth)

        assertEquals(expected, actual)
    }
}