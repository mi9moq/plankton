package com.example.plancton.di

import com.example.plancton.core.dagger.AppScope
import com.example.plancton.data.datasource.EventRemoteDataSource
import com.example.plancton.data.datasource.EventRemoteDataSourceImpl
import com.example.plancton.data.datasource.TokenLocalDataSource
import com.example.plancton.data.datasource.TokenLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    @AppScope
    fun bindEventRemoteDataSource(impl: EventRemoteDataSourceImpl): EventRemoteDataSource

    @Binds
    @AppScope
    fun bindTokenLocalDataSource(impl: TokenLocalDataSourceImpl): TokenLocalDataSource
}