package com.example.plancton.core.token

import com.example.plancton.core.dagger.AppScope
import com.example.plancton.core.token.data.datasource.TokenLocalDataSource
import com.example.plancton.core.token.data.datasource.TokenLocalDataSourceImpl
import com.example.plancton.core.token.data.repository.TokenRepositoryImpl
import com.example.plancton.core.token.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module

@Module
interface TokenCoreModule {

    @Binds
    @AppScope
    fun bindTokenLocalDataSource(impl: TokenLocalDataSourceImpl): TokenLocalDataSource

    @Binds
    @AppScope
    fun bindTokenRepository(impl: TokenRepositoryImpl): TokenRepository
}