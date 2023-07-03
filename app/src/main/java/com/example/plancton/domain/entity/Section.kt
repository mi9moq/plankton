package com.example.plancton.domain.entity

data class Section(
    val title: String,
    val isPublic: Boolean,
    val url: String,
    val user: User,
    val access: Access
)