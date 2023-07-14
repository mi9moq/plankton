package com.example.plancton.core.auth

import com.example.plancton.core.auth.data.datasource.AuthRemoteDataSource
import com.example.plancton.core.auth.data.datasource.AuthRemoteDataSourceImpl
import com.example.plancton.core.auth.data.network.api.AuthApi
import com.example.plancton.core.auth.data.repository.AuthRepositoryImpl
import com.example.plancton.core.auth.domain.repository.AuthRepository
import com.example.plancton.core.dagger.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
interface AuthCoreModule {

    @Binds
    @AppScope
    fun bindAuthRemoteDataSource(impl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @AppScope
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    companion object {

        @AppScope
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi =
            retrofit.create()
    }
}