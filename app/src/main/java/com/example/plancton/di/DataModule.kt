package com.example.plancton.di

import com.example.plancton.data.datasource.EventRemoteDataSource
import com.example.plancton.data.datasource.EventRemoteDataSourceImpl
import com.example.plancton.data.repository.EventRepositoryImpl
import com.example.plancton.domain.repository.EventRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @AppScope
    fun bindEventRepository(impl: EventRepositoryImpl): EventRepository

    @Binds
    @AppScope
    fun bindEventRemoteDataSource(impl: EventRemoteDataSourceImpl): EventRemoteDataSource
}