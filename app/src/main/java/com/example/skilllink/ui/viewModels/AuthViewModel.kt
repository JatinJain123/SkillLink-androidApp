package com.example.skilllink.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilllink.domain.model.local.UiEvent
import com.example.skilllink.domain.model.remote.AuthResponse
import com.example.skilllink.domain.model.remote.LoginRequest
import com.example.skilllink.domain.model.remote.SetSecretPinRequest
import com.example.skilllink.domain.model.remote.SetUsernameRequest
import com.example.skilllink.domain.repository.AuthRepository
import com.example.skilllink.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent:SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun signup(
        email: String,
        password: String,
        appStoreViewModel: AppStoreViewModel,
    ) = sendAuthRequest(email, password, appStoreViewModel, true)

    fun login(
        email: String,
        password: String,
        appStoreViewModel: AppStoreViewModel,
    ) = sendAuthRequest(email, password, appStoreViewModel, false)

    private fun sendAuthRequest(
        email: String,
        password: String,
        appStoreViewModel: AppStoreViewModel,
        isSignup: Boolean
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            if(password.length < 8) {
                _uiEvent.emit(
                    UiEvent.ShowSnackBar(message = "password cannot be less than 8 characters")
                )
                return@launch
            }
            val request = LoginRequest(email = email, password = password)
            val result =  if(isSignup) authRepository.signup(request = request) else authRepository.login(request = request)
            _isLoading.value = false
            handleLoginResult(result = result, appStoreViewModel = appStoreViewModel)
        }
    }

    private suspend fun handleLoginResult(
        result: NetworkResult<AuthResponse>,
        appStoreViewModel: AppStoreViewModel
    ) {
        when(result) {
            is NetworkResult.Success -> {
                result.data?.let {
                    val message = it.message
                    val userId = it.userId
                    val email = it.email
                    _uiEvent.emit(UiEvent.ShowSnackBar(message = message))
                    appStoreViewModel.setCurrentUser(userId = userId)
                    appStoreViewModel.setCurrentEmail(email = email)
                    _uiEvent.emit(UiEvent.NavigateToUsernameScreen)
                }
            }
            is NetworkResult.Error -> {
                result.message?.let { _uiEvent.emit(UiEvent.ShowSnackBar(message = it)) }
            }
            is NetworkResult.Loading -> {}
        }
    }

    fun setUsername(
        username: String,
        appStoreViewModel: AppStoreViewModel,
        userPrefsStoreViewModel: UserPrefsStoreViewModel?
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val userid = appStoreViewModel.currentUser.value
            val email = appStoreViewModel.currentEmail.value
            val request = SetUsernameRequest(userId = userid, email = email, username = username)
            val result = authRepository.setUsername(request = request)
            _isLoading.value = false
            handlePatchResult(
                result = result,
                uiEvent = UiEvent.NavigateToSecretPinScreen,
                onSuccess = {
                    userPrefsStoreViewModel?.apply {
                        setUsername(username = username)
                    }
                }
            )
        }
    }

    fun setSecretPin(
        secretPin: Int,
        appStoreViewModel: AppStoreViewModel,
        userPrefsStoreViewModel: UserPrefsStoreViewModel?
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val userid = appStoreViewModel.currentUser.value
            val email = appStoreViewModel.currentEmail.value
            val request = SetSecretPinRequest(userId = userid, email = email, secretPin = secretPin)
            val result = authRepository.setSecretPin(request = request)
            _isLoading.value = false
            handlePatchResult(
                result = result,
                uiEvent = UiEvent.NavigateToHomeScreen,
                onSuccess = {
                    userPrefsStoreViewModel?.apply {
                        setHasSecretPin(hasSpin = true)
                    }
                }
            )
        }
    }

    private suspend fun handlePatchResult(
        result: NetworkResult<AuthResponse>,
        uiEvent: UiEvent,
        onSuccess: () -> Unit
    ) {
        when(result) {
            is NetworkResult.Success -> {
                result.data?.let {
                    val message = it.message
                    _uiEvent.emit(UiEvent.ShowSnackBar(message = message))
                    onSuccess()
                    _uiEvent.emit(uiEvent)
                }
            }
            is NetworkResult.Error -> {
                result.message?.let { _uiEvent.emit(UiEvent.ShowSnackBar(message = it)) }
            }
            is NetworkResult.Loading -> {}
        }
    }
}