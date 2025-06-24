package com.example.skilllink.data.remote

import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.model.remote.LoginRequest
import com.example.skilllink.domain.model.remote.SetSecretPinRequest
import com.example.skilllink.domain.model.remote.SetUsernameRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/auth/signup")
    suspend fun signup(@Body request: LoginRequest): Response<AuthResponse>

    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @PATCH("/api/auth/user/username")
    suspend fun setUsername(@Body request: SetUsernameRequest): Response<AuthResponse>

    @PATCH("/api/auth/user/secret-pin")
    suspend fun setSecretPin(@Body request: SetSecretPinRequest): Response<AuthResponse>
}