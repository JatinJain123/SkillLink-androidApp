package com.example.skilllink.domain.model.local

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.skilllink.ui.viewModels.AppStoreViewModel
import com.example.skilllink.ui.viewModels.UserPrefsStoreViewModel

data class AppDependencies (
    val appStoreViewModel: AppStoreViewModel,
    val userPrefsStoreViewModel: UserPrefsStoreViewModel?,
    val isDarkTheme: Boolean
)

val LocalAppDependencies = staticCompositionLocalOf<AppDependencies> {
    error("AppDependencies not provided")
}