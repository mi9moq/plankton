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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

        private const val BASE_URL = "http://81.177.197.88:8080/"

        @AppScope
        @Provides
        fun provideHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        @AppScope
        @Provides
        fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @AppScope
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi =
            retrofit.create()
    }
}