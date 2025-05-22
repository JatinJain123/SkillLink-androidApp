package com.example.skilllink.domain.model.local

import androidx.compose.runtime.staticCompositionLocalOf

data class UiStates (
    val isUserSetupComplete: Boolean,
    val isDarkTheme: Boolean
)

val LocalUiStates = staticCompositionLocalOf<UiStates> {
    error("UiStates not provided")
}