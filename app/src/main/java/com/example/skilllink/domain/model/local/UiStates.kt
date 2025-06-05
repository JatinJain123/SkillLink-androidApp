package com.example.skilllink.domain.model.local

import androidx.compose.runtime.staticCompositionLocalOf

data class UiStates (
    val isLogged: Boolean,
    val hasUsername: Boolean,
    val hasSecretPin: Boolean,
    val isUserSetupComplete: Boolean,
    val isDarkTheme: Boolean,
    var mikeOn: Boolean = false,
)

val LocalUiStates = staticCompositionLocalOf<UiStates> {
    error("UiStates not provided")
}