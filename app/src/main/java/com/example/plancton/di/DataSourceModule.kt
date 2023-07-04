package com.example.plancton.di

import com.example.plancton.data.datasource.AuthRemoteDataSource
import com.example.plancton.data.datasource.AuthRemoteDataSourceImpl
import com.example.plancton.data.datasource.EventRemoteDataSource
import com.example.plancton.data.datasource.EventRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    @AppScope
    fun bindEventRemoteDataSource(impl: EventRemoteDataSourceImpl): EventRemoteDataSource

    @AppScope
    @Binds
    fun bindAuthRemoteDataSource(impl: AuthRemoteDataSourceImpl): AuthRemoteDataSource
}