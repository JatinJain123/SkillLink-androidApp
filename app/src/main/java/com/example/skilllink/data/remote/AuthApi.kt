package com.example.skilllink.data.remote

import com.example.skilllink.domain.model.remote.AuthBody
import com.example.skilllink.domain.model.remote.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/signup")
    suspend fun signup(@Body authBody: AuthBody): Response<AuthResponse>

    @POST("/auth/login")
    suspend fun login(@Body authBody: AuthBody): Response<AuthResponse>
}