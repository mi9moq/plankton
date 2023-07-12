package com.example.plancton.data.network.api

import com.example.plancton.data.datasource.TokenLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        tokenLocalDataSource.get()?.let {
            requestBuilder.addHeader("access-token", it)
        }

        return chain.proceed(requestBuilder.build())
    }
}