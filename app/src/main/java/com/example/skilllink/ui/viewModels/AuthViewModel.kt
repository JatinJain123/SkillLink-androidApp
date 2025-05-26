package com.example.skilllink.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilllink.domain.model.remote.AuthBody
import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.repository.AuthRepository
import com.example.skilllink.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _authResponse = MutableStateFlow<NetworkResult<AuthResponse>>(NetworkResult.Loading())
    val authResponse: StateFlow<NetworkResult<AuthResponse>> = _authResponse

    private val _uiEvent = MutableSharedFlow<String>()
    val uiEvent:SharedFlow<String> = _uiEvent.asSharedFlow()

    val isLoading: StateFlow<Boolean> = _authResponse
        .map { it is NetworkResult.Loading }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    fun signup(
        email: String,
        password: String,
        appStoreViewModel: AppStoreViewModel,
    ) {
        viewModelScope.launch {
            if(password.length < 8) {
                _uiEvent.emit("Password cannot be less than 8 characters")
                return@launch
            }

            val authBody = AuthBody(email = email, password = password)
            _authResponse.value = NetworkResult.Loading()
            val result =  authRepository.signup(authBody = authBody)
            _authResponse.value = result
            handleAuthResult(result = result, appStoreViewModel = appStoreViewModel)
        }
    }

    fun login(
        email: String,
        password: String,
        appStoreViewModel: AppStoreViewModel,
    ) {
        viewModelScope.launch {
            if(password.length < 8) {
                _uiEvent.emit("Password cannot be less than 8 characters")
                return@launch
            }

            val authBody = AuthBody(email = email, password = password)
            _authResponse.value = NetworkResult.Loading()
            val result =  authRepository.login(authBody = authBody)
            _authResponse.value = result
            handleAuthResult(result = result, appStoreViewModel = appStoreViewModel)
        }
    }

    private suspend fun handleAuthResult(
        result: NetworkResult<AuthResponse>,
        appStoreViewModel: AppStoreViewModel
    ) {
        when(result) {
            is NetworkResult.Success -> {
                result.data?.let {
                    val userId = it.userId
                    val email = it.email
                    appStoreViewModel.setCurrentUser(userId = userId)
                    appStoreViewModel.setCurrentEmail(email = email)
                }
                _uiEvent.emit("Registration Successful")
            }
            is NetworkResult.Error -> {
                result.message?.let { _uiEvent.emit(it) }
            }
            is NetworkResult.Loading -> {}
        }
    }
}