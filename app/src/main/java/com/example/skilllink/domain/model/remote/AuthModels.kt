package com.example.skilllink.domain.model.remote

data class LoginRequest (
    val email: String,
    val password: String
)

data class AuthResponse (
    val success: Boolean,
    val message: String,
    val userId: String,
    val email: String
)