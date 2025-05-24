package com.example.skilllink.domain.model.remote

data class AuthBody (
    val email: String,
    val password: String
)

data class AuthResponse (
    val message: String,
    val userId: String,
    val email: String
)