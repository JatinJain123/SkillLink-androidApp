package com.example.skilllink.data.repositoryImpl

import com.example.skilllink.data.remote.AuthApi
import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.model.remote.LoginRequest
import com.example.skilllink.domain.repository.AuthRepository
import com.example.skilllink.utils.NetworkResult
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
): AuthRepository {
    override suspend fun signup(request: LoginRequest): NetworkResult<AuthResponse> {
        val response = authApi.signup(request = request)
        return checkResponse(response = response)
    }

    override suspend fun login(request: LoginRequest): NetworkResult<AuthResponse> {
        val response = authApi.login(request = request)
        return checkResponse(response = response)
    }

    private fun checkResponse(response: Response<AuthResponse>): NetworkResult<AuthResponse> {
        return try {
            if(response.isSuccessful) {
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
        } catch (e: Exception) {
            NetworkResult.Error("ERROR: ${e.message}")
        }
    }
}