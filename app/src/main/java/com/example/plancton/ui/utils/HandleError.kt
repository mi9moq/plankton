package com.example.plancton.ui.utils

import com.examlpe.plancton.core.event.domain.entity.EventErrorType
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun handleEventError(throwable: Throwable): EventErrorType =
    when (throwable) {
        is UnknownHostException, is SocketTimeoutException, is ConnectException -> EventErrorType.CONNECTION
        is HttpException -> {
            when (throwable.code()) {
                401 -> EventErrorType.UNAUTHORIZED
                404 -> EventErrorType.NOT_FOUND
                else -> EventErrorType.UNKNOWN
            }
        }

        else -> EventErrorType.UNKNOWN
    }