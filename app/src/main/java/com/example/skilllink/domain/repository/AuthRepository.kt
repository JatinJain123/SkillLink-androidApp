package com.example.skilllink.domain.repository

import com.example.skilllink.domain.model.remote.AuthBody
import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.utils.NetworkResult

interface AuthRepository {
    suspend fun signup(authBody: AuthBody): NetworkResult<AuthResponse>
    suspend fun login(authBody: AuthBody): NetworkResult<AuthResponse>
}