package com.example.plancton.core.user.domain.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("full_name") val fullName: String,
)
