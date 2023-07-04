package com.example.plancton.di


import com.example.plancton.data.repository.EventRepositoryImpl
import com.example.plancton.domain.repository.EventRepository
import com.example.plancton.data.repository.AuthRepositoryImpl
import com.example.plancton.domain.repository.AuthRepository
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
}