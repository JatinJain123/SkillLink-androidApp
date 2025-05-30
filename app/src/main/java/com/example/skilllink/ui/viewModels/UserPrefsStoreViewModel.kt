package com.example.skilllink.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.skilllink.data.local.managers.UserPrefsStoreManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserPrefsStoreViewModelFactory(
    private val userPrefsStoreManager: UserPrefsStoreManager,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserPrefsStoreViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserPrefsStoreViewModel(userPrefsStoreManager) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }
}

class UserPrefsStoreViewModel(
    private val userPrefsStoreManager: UserPrefsStoreManager
): ViewModel() {
    fun setId(userId: String) {
        viewModelScope.launch {
            userPrefsStoreManager.setId(userId)
        }
    }

    fun setUsername(username: String) {
        viewModelScope.launch {
            userPrefsStoreManager.setUsername(username)
        }
    }

    fun setEmail(email: String) {
        viewModelScope.launch {
            userPrefsStoreManager.setEmail(email)
        }
    }

    fun setHasSpin(hasSpin: Boolean) {
        viewModelScope.launch {
            userPrefsStoreManager.setHasSpin(hasSpin)
        }
    }

    fun setLightMode(state: Boolean) {
        viewModelScope.launch {
            userPrefsStoreManager.setLightMode(state)
        }
    }

    fun setProfilePic(uri: String) {
        viewModelScope.launch {
            userPrefsStoreManager.setProfilePic(uri)
        }
    }

    val id: StateFlow<String> = userPrefsStoreManager.id.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val email: StateFlow<String> = userPrefsStoreManager.email.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val userName: StateFlow<String> = userPrefsStoreManager.userName.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val hasSpin: StateFlow<Boolean> = userPrefsStoreManager.hasSpin.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val lightMode: StateFlow<Boolean> = userPrefsStoreManager.lightMode.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val profilePic: StateFlow<String> = userPrefsStoreManager.profilePic.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )
}