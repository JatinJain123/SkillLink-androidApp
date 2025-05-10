package com.example.skilllink.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilllink.data.local.AppStoreManager
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

    val notificationsEnabled: StateFlow<Boolean> = appStoreManager.notificationsEnabled.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    fun setCurrentUser(userId: String) {
        viewModelScope.launch {
            appStoreManager.setCurrentUser(userId = userId)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            appStoreManager.setNotifications(enabled = enabled)
        }
    }
}