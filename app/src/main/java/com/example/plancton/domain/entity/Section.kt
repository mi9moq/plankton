package com.example.plancton.domain.entity

import java.net.URL

data class Section(
    val title: String,
    var isPublic: Boolean = false,
    var url: URL? = null,
    val owner: User,
    var accessType: AccessType = AccessType.Editor,
)
