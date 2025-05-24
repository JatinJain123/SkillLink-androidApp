package com.example.skilllink.domain.repository

import com.example.skilllink.domain.model.remote.AuthBody
import com.example.skilllink.domain.model.remote.AuthResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun signup(authBody: AuthBody): Response<AuthResponse>
    suspend fun login(authBody: AuthBody): Response<AuthResponse>
}