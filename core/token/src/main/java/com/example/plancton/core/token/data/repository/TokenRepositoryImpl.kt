package com.example.plancton.core.token.data.repository

import com.example.plancton.core.token.data.datasource.TokenLocalDataSource
import com.example.plancton.core.token.domain.repository.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val dataSource: TokenLocalDataSource,
) : TokenRepository {

    override fun get(): String? =
        dataSource.get()

    override fun set(token: String) {
        dataSource.set(token)
    }

    override fun delete() {
        dataSource.delete()
    }
}