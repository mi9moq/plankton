package com.example.plancton.core.user

import com.example.plancton.core.dagger.AppScope
import com.example.plancton.core.user.data.api.UserApi
import com.example.plancton.core.user.data.datasource.UserRemoteDataSource
import com.example.plancton.core.user.data.datasource.UserRemoteDataSourceImpl
import com.example.plancton.core.user.data.repository.UserRepositoryImpl
import com.example.plancton.core.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Module
interface UserCoreModule {

    companion object {
        @AppScope
        @Provides
        fun provideUserApi(retrofit: Retrofit): UserApi =
            retrofit.create()
    }

    @Binds
    @AppScope
    fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @AppScope
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}