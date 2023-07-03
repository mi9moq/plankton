package com.example.plancton.di

import com.example.plancton.data.datasource.AuthRemoteDataSource
import com.example.plancton.data.datasource.AuthRemoteDataSourceImpl
import com.example.plancton.data.repository.AuthRepositoryImpl
import com.example.plancton.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @AppScope
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @AppScope
    @Binds
    fun bindAuthRemoteDataSource(impl: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}