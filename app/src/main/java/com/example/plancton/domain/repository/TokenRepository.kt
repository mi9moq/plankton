package com.example.plancton.domain.repository

interface TokenRepository {

    fun get(): String

    fun set(token: String)

    fun delete()
}