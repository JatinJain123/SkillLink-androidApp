package com.example.skilllink.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object LoginScreen

    @Serializable
    data object DashBoard
}