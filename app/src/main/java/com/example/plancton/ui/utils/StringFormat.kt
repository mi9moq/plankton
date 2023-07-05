package com.example.plancton.ui.utils

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val RUSSIAN_LOCALE = Locale("ru", "RU")

fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("EE dd MMM yyyy", RUSSIAN_LOCALE)
    val formattedDate = dateFormat.format(date)
    return formattedDate.toString()
}

fun formatTime(time: Time): String {
    val format = SimpleDateFormat("HH:mm", RUSSIAN_LOCALE)
    return format.format(time)
}