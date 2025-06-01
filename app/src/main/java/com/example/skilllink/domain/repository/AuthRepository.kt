package com.example.skilllink.domain.repository

import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.model.remote.LoginRequest
import com.example.skilllink.utils.NetworkResult

interface AuthRepository {
    suspend fun signup(request: LoginRequest): NetworkResult<AuthResponse>
    suspend fun login(request: LoginRequest): NetworkResult<AuthResponse>
}