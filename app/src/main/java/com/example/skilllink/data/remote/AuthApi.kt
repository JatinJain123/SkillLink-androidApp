package com.example.skilllink.data.remote

import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.model.remote.LoginRequest
import com.example.skilllink.domain.model.remote.SetSecretPinRequest
import com.example.skilllink.domain.model.remote.SetUsernameRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/signup")
    suspend fun signup(@Body request: LoginRequest): Response<AuthResponse>

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/setUsername")
    suspend fun setUsername(@Body request: SetUsernameRequest): Response<AuthResponse>

    @POST("auth/setSecretPin")
    suspend fun setSecretPin(@Body request: SetSecretPinRequest): Response<AuthResponse>
}