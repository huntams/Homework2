package com.example.homework2.data.model

data class ProfileCompact(
    val id : String,
    val username : String,
    val displayName : String?,
    val avatarUrl : String?,
    val subscribed : Boolean,
)
