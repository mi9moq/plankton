package com.example.plancton.di


import com.example.plancton.core.auth.data.repository.AuthRepositoryImpl
import com.example.plancton.data.repository.EventRepositoryImpl
import com.example.plancton.data.repository.TokenRepositoryImpl
import com.example.plancton.core.auth.domain.repository.AuthRepository
import com.example.plancton.domain.repository.EventRepository
import com.example.plancton.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {


    @Binds
    @AppScope
    fun bindEventRepository(impl: EventRepositoryImpl): EventRepository

    @Binds
    @AppScope
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @AppScope
    fun bindTokenRepository(impl: TokenRepositoryImpl): TokenRepository
}