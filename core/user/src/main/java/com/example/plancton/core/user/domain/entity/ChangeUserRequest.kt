package com.example.plancton.core.user.domain.entity

import com.google.gson.annotations.SerializedName

data class ChangeUserRequest(
    @SerializedName("full_name") val fullName: String,
)
