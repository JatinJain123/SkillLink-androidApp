package com.example.skilllink.domain.model.local

sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
    data object NavigateToUsernameScreen: UiEvent()
    data object NavigateToSecretPinScreen: UiEvent()
    data object NavigateToHomeScreen: UiEvent()
}