package com.example.skilllink.data.repositoryImpl

import com.example.skilllink.data.remote.AuthApiService
import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.model.remote.LoginRequest
import com.example.skilllink.domain.model.remote.SetSecretPinRequest
import com.example.skilllink.domain.model.remote.SetUsernameRequest
import com.example.skilllink.domain.repository.AuthRepository
import com.example.skilllink.utils.NetworkResult
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
): AuthRepository {
    override suspend fun signup(request: LoginRequest): NetworkResult<AuthResponse> {
        try {
            val response = authApiService.signup(request = request)
            return checkResponse(response = response)
        } catch (e: Exception) {
            return NetworkResult.Error("ERROR: network call error ${e.message}")
        }
    }

    override suspend fun login(request: LoginRequest): NetworkResult<AuthResponse> {
        try {
            val response = authApiService.login(request = request)
            return checkResponse(response = response)
        } catch (e: Exception) {
            return NetworkResult.Error("ERROR: network call error ${e.message}")
        }
    }

    override suspend fun setUsername(request: SetUsernameRequest): NetworkResult<AuthResponse> {
        try {
            val response = authApiService.setUsername(request = request)
            return checkResponse(response = response)
        } catch (e: Exception) {
            return NetworkResult.Error("ERROR: network call error ${e.message}")
        }
    }

    override suspend fun setSecretPin(request: SetSecretPinRequest): NetworkResult<AuthResponse> {
        try {
            val response = authApiService.setSecretPin(request = request)
            return checkResponse(response = response)
        } catch (e: Exception) {
            return NetworkResult.Error("ERROR: network call error ${e.message}")
        }
    }

    private fun checkResponse(response: Response<AuthResponse>): NetworkResult<AuthResponse> {
        return if(response.isSuccessful) {
            response.body()?.let {
                if(it.success) {
                    NetworkResult.Success(it)
                } else {
                    NetworkResult.Error("ERROR: ${it.message}")
                }
            } ?: NetworkResult.Error("ERROR: response body is null")
        } else {
            val errorBody = response.errorBody()
            val errorBodyFormatted: AuthResponse? = try {
                errorBody?.charStream().use {
                    Gson().fromJson(it, AuthResponse::class.java)
                }
            } catch (e: Exception) {
                null
            }

            NetworkResult.Error(
                "ERROR: ${errorBodyFormatted?.message ?: "some unexpected network error"}"
            )
        }
    }
}