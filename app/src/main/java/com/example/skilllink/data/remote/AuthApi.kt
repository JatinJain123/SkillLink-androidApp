package com.example.skilllink.data.remote

import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.model.remote.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/signup")
    suspend fun signup(@Body request: LoginRequest): Response<AuthResponse>

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
}