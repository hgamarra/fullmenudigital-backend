package com.example.data.model

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable

@Serializable
data class User(

    @Nullable
    val id : Int? = -1,
    val name: String,
    val email: String,
    val password: String
)
