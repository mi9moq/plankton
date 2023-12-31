package com.example.plancton.ui.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

val RUSSIAN_LOCALE = Locale("ru", "RU")

val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()

fun formatDate(date: LocalDate): String =
    date.format(DateTimeFormatter.ofPattern("EE dd MMM yyyy", RUSSIAN_LOCALE))

fun formatTime(time: LocalTime): String =
    time.format(DateTimeFormatter.ofPattern("HH:mm", RUSSIAN_LOCALE))