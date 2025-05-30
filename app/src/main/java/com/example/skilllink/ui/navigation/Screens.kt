package com.example.skilllink.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    sealed class AuthScreens{
        @Serializable
        data object LoginScreen

        @Serializable
        data object UserNameScreen

        @Serializable
        data object SecretPinScreen
    }


    sealed class HomeScreens {
        @Serializable
        data object HomeScreen
    }
}