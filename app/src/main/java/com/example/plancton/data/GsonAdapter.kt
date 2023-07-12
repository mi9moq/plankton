package com.example.plancton.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object LocalTimeGsonAdapter : JsonDeserializer<LocalTime>, JsonSerializer<LocalTime>{

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalTime {
        val stringTime = json.asString
        return LocalTime.parse(stringTime, DateTimeFormatter.ISO_TIME)
    }

    override fun serialize(
        src: LocalTime,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.format(DateTimeFormatter.ISO_TIME))
    }
}

object LocalDateGsonAdapter : JsonDeserializer<LocalDate>,JsonSerializer<LocalDate> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDate {
        val stringDate = json.asString
        return LocalDate.parse(stringDate, DateTimeFormatter.ISO_DATE)
    }

    override fun serialize(
        src: LocalDate,
        typeOfSrc: Type,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.format(DateTimeFormatter.ISO_DATE))
    }
}