package com.example.plancton.core.token.domain.repository

interface TokenRepository {

    fun get(): String?

    fun set(token: String)

    fun delete()
}