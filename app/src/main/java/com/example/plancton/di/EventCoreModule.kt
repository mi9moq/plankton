package com.example.plancton.di

import com.example.plancton.core.dagger.AppScope
import com.example.plancton.data.LocalDateGsonAdapter
import com.example.plancton.data.LocalTimeGsonAdapter
import com.example.plancton.data.datasource.EventRemoteDataSource
import com.example.plancton.data.datasource.EventRemoteDataSourceImpl
import com.example.plancton.data.network.api.AuthInterceptor
import com.example.plancton.data.network.api.EventApi
import com.example.plancton.data.repository.EventRepositoryImpl
import com.example.plancton.domain.repository.EventRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.time.LocalDate
import java.time.LocalTime

@Module
interface EventCoreModule {

    @Binds
    @AppScope
    fun bindEventRemoteDataSource(impl: EventRemoteDataSourceImpl): EventRemoteDataSource

    @Binds
    @AppScope
    fun bindEventRepository(impl: EventRepositoryImpl): EventRepository

    companion object {

        private const val BASE_URL = "http://81.177.197.88:8080/"

        @AppScope
        @Provides
        fun provideHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor(authInterceptor)
                .build()

        @AppScope
        @Provides
        fun provideGson(): Gson = GsonBuilder()
            .registerTypeAdapter(LocalTime::class.java, LocalTimeGsonAdapter)
            .registerTypeAdapter(LocalDate::class.java, LocalDateGsonAdapter)
            .create()

        @AppScope
        @Provides
        fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        @AppScope
        @Provides
        fun provideEventApi(retrofit: Retrofit): EventApi =
            retrofit.create()
    }
}