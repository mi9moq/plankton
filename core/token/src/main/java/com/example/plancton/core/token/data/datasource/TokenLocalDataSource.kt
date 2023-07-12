package com.example.plancton.core.token.data.datasource

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

interface TokenLocalDataSource {

    fun get(): String?

    fun set(token: String)

    fun delete()
}

class TokenLocalDataSourceImpl @Inject constructor(
    private val context: Context,
) : TokenLocalDataSource {

    companion object {
        private const val PREFS_NAME = "token"
        private const val TOKEN_KEY = "access token"

        private const val EMPTY_VALUE = ""
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    //TODO выглядит не очень; поменять возвращаемый тип запроса согласно api
    override fun get(): String? =
        sharedPreferences.getString(TOKEN_KEY, null)


    override fun set(token: String) {
        sharedPreferences
            .edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    override fun delete() {
        sharedPreferences
            .edit()
            .putString(TOKEN_KEY, EMPTY_VALUE)
            .apply()
    }
}