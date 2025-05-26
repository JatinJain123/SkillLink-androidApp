package com.example.skilllink.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilllink.data.local.managers.AppStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppStoreViewModel @Inject constructor(
    private val appStoreManager: AppStoreManager
): ViewModel() {
    val currentUser: StateFlow<String> = appStoreManager.currentUser.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val currentEmail: StateFlow<String> = appStoreManager.currentEmail.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    fun setCurrentUser(userId: String) {
        viewModelScope.launch {
            appStoreManager.setCurrentUser(userId = userId)
        }
    }

    fun setCurrentEmail(email: String) {
        viewModelScope.launch {
            appStoreManager.setCurrentEmail(email = email)
        }
    }
}