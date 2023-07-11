package com.example.plancton.core.auth.data.network.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val token: String
)
