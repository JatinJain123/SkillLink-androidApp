package com.example.skilllink.domain.model.remote

data class LoginRequest (
    val email: String,
    val password: String
)

data class SetUsernameRequest(
    val userId: String,
    val email: String,
    val username: String
)

data class SetSecretPinRequest(
    val userId: String,
    val email: String,
    val secretPin: Int
)

data class AuthResponse (
    val success: Boolean,
    val message: String,
    val userId: String,
    val email: String
)