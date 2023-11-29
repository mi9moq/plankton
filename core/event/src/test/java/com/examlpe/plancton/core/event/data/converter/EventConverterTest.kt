package com.examlpe.plancton.core.event.data.converter

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import utils.EventData

class EventConverterTest {

    private val converter = EventConverter()

    private val dto = EventData.eventDto
    private val entity = EventData.event

    @Test
    fun `convert EXPECT dto`() {

        val expected = dto
        val actual = converter.convert(entity)

        assertEquals(expected, actual)
    }

    @Test
    fun `revert EXPECT entity`() {

        val expected = entity
        val actual = converter.revert(dto)

        assertEquals(expected, actual)
    }
}