package com.example.plancton.core.auth

import com.example.plancton.core.auth.data.datasource.AuthRemoteDataSource
import com.example.plancton.core.auth.data.datasource.AuthRemoteDataSourceImpl
import com.example.plancton.core.auth.data.repository.AuthRepositoryImpl
import com.example.plancton.core.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AuthCoreModule {

    @Binds
    @AppScope
    fun bindAuthRemoteDataSource(impl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @AppScope
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}